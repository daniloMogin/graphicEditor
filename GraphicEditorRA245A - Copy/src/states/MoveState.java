package states;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import models.elements.DiagramDevice;
import models.elements.DiagramElement;
import workspace.view.DiagramView;

public class MoveState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6296652934933206078L;

	private DiagramView med;

	public MoveState(DiagramView md) {
		med = md;

	}

	public void mouseDragged(MouseEvent e) {

		med.getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		Point2D lastPosition = e.getPoint();
		med.transformToUserSpace(lastPosition);

		double xx = med.getLastPosition().getX() - lastPosition.getX();
		double yy = med.getLastPosition().getY() - lastPosition.getY();
		// pomeranje elementa:
		Iterator<DiagramElement> it = med.getDiagram().getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {

				DiagramDevice device = (DiagramDevice) element;
				Point2D newPosition = (Point2D) device.getPosition().clone();
				newPosition.setLocation(newPosition.getX() - xx,
						newPosition.getY() - yy);
				device.setPosition(newPosition);

			}
		}
		med.setLastPosition(lastPosition);
		med.updatePerformed(null);

	}

	public void mouseReleased(MouseEvent e) {

		med.getFramework().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		med.startSelectState();
	}

}
