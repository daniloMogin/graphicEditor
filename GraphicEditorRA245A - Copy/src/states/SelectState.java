package states;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import app.MainFrame;
import models.elements.DiagramElement;
import workspace.view.DiagramView;
import workspace.view.DiagramView.Handle;

public class SelectState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350348504985326993L;

	private DiagramView med;

	// indeks elementa koji je selektovan
	private int elementInMotion = -1;
	private Handle handleInMotion = null;

	private int mouseButton = 0;

	public SelectState(DiagramView md) {
		med = md;
	}

	public void mousePressed(MouseEvent e) {

		mouseButton = e.getButton();
		Point position = e.getPoint();
		med.transformToUserSpace(position);
		if (e.getButton() == MouseEvent.BUTTON1) {
			handleInMotion = med.getDeviceAndHandleForPoint(position);
			if (handleInMotion == null) {
				if (!e.isControlDown()) {
					med.getDiagram().getSelectionModel()
							.removeAllFromSelectionList();
				}
				elementInMotion = med.getDiagram().getModel()
						.getElementAtPosition(position);
				if (elementInMotion != -1) {
					// pogodjen je element, ukoliko je selektovan treba ga
					// ukloniti iz liste,
					// ako nije treba ga dodati u listu
					DiagramElement element = med.getDiagram().getModel()
							.getElementAt(elementInMotion);

					if (med.getDiagram().getSelectionModel()
							.isElementSelected(element)) {
						med.getDiagram().getSelectionModel()
								.removeFromSelectionList(element);
					} else {
						med.getDiagram().getSelectionModel()
								.addToSelectionList(element);
					}

				} else {
					// nije pogodjen nijedan element

				}
			} else {
				// pogodjen je handl nad nekim elementom, potrebno je raditi
				// resize elementa
				med.startResizeState();
			}

		}

	}

	public void mouseMoved(MouseEvent e) {
		// Promena pokazivača miša u zavisnosti od toga iznad čega se nalazi
		Point2D position = e.getPoint();
		med.transformToUserSpace(position);
		med.setMouseCursor(position);
		MainFrame.getInstance().getStatusBar()
				.setPosition(e.getPoint().toString());
	}

	public void mouseDragged(MouseEvent e) {
		if (mouseButton == MouseEvent.BUTTON1) {
			// vrši se povlačenje sa levim tasterom miša
			// provera da li je selektovan handle elementa, tada se radi resize
			// elementa
			Point position = e.getPoint();
			med.transformToUserSpace(position);
			handleInMotion = med.getDeviceAndHandleForPoint(position);
			if (handleInMotion != null) {
				med.startResizeState();
			} else {
				// nije selektovan handle, da li je selektovan element
				elementInMotion = med.getDiagram().getModel()
						.getElementAtPosition(position);
				if (elementInMotion != -1) {
					// selektovan je element ili grupa elemenata
					// preci u MoveState
					med.startMoveState();
					return;

				} else
					// nije pogodjen element, prelazimo u Laso stanje
					med.startLassoState();
			}
		}

	}
}
