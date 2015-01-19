package states;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import models.elements.CircleElement;
import models.elements.DiagramElement;
import models.elements.RectangleElement;
import models.elements.StarElement;
import models.elements.TriangleElement;
import workspace.view.DiagramView;
import workspace.view.DiagramView.Handle;
import app.MainFrame;
import dialogs.ElementChangeDialog;

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
					if (med.getDiagram().getSelectionModel()
							.getSelectionListSize() == 1) {
						MainFrame.getInstance().getStatusBar()
								.setElementName(element.toString());

						if (element instanceof RectangleElement) {
							MainFrame.getInstance().getStatusBar()
									.setElementType("Rectangle");
							MainFrame
									.getInstance()
									.getStatusBar()
									.setDimension(
											((RectangleElement) element)
													.getSize().height,
											((RectangleElement) element)
													.getSize().width);
						} else if (element instanceof CircleElement) {
							MainFrame.getInstance().getStatusBar()
									.setElementType("Circle");
							MainFrame
									.getInstance()
									.getStatusBar()
									.setDimension(
											((CircleElement) element).getSize().height,
											((CircleElement) element).getSize().width);
						} else if (element instanceof TriangleElement) {
							MainFrame.getInstance().getStatusBar()
									.setElementType("Triangle");
							MainFrame
									.getInstance()
									.getStatusBar()
									.setDimension(
											((TriangleElement) element)
													.getSize().height,
											((TriangleElement) element)
													.getSize().width);
						} else if (element instanceof StarElement) {
							MainFrame.getInstance().getStatusBar()
									.setElementType("Star");
							MainFrame
									.getInstance()
									.getStatusBar()
									.setDimension(
											((StarElement) element).getSize().height,
											((StarElement) element).getSize().width);
						}

					} else {
						// selektovano je vise elemenata
						MainFrame.getInstance().getStatusBar()
								.setElementName("");
						MainFrame.getInstance().getStatusBar()
								.setElementType("");
						MainFrame.getInstance().getStatusBar()
								.setDimension(-1, -1);

					}
				} else {
					// nije pogodjen nijedan element
					MainFrame.getInstance().getStatusBar().setElementType("");
					MainFrame.getInstance().getStatusBar().setElementName("");
					MainFrame.getInstance().getStatusBar().setDimension(-1, -1);

				}
			} else {
				// pogodjen je handl nad nekim elementom, potrebno je raditi
				// resize elementa
				med.startResizeState();
			}

		}
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2
				&& !e.isControlDown()) {
			if (med.getDiagram().getSelectionModel().getSelectionList().size() == 1) {
				ElementChangeDialog ed = new ElementChangeDialog(
						MainFrame.getInstance(), null, med.getDiagram()
								.getSelectionModel().getSelectionList().get(0));
				ed.setVisible(true);
			}
		}

	}

	public void mouseMoved(MouseEvent e) {
		// Promena pokazivača miša u zavisnosti od toga iznad čega se nalazi
		Point2D position = e.getPoint();
		String posString = "Position [x = " + e.getPoint().x + ", y = "
				+ e.getPoint().y + "]";

		med.transformToUserSpace(position);
		med.setMouseCursor(position);
		MainFrame.getInstance().getStatusBar().setPosition(posString);
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
