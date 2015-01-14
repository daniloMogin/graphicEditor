package states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import models.elements.DiagramDevice;
import models.elements.DiagramElement;
import workspace.view.DiagramView;
import workspace.view.DiagramView.Handle;

public class ResizeState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6965524213481718446L;

	Handle handleInMotion = null;
	private DiagramView med;

	public ResizeState(DiagramView md) {
		med = md;
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

		Point2D position = e.getPoint();
		med.transformToUserSpace(position);
		if (handleInMotion == null) {
			handleInMotion = med.getDeviceAndHandleForPoint(position);
		}
		if (handleInMotion != null) {

			Iterator<DiagramElement> it = med.getDiagram().getSelectionModel()
					.getSelectionListIterator();
			while (it.hasNext()) {

				DiagramElement element = it.next();
				if (element instanceof DiagramDevice) {
					DiagramDevice device = (DiagramDevice) element;
					switch (handleInMotion.ordinal()) {
					// southeast
					case 4: {
						double deltaX = position.getX()
								- (device.getPosition().getX() + device
										.getSize().getWidth());
						double deltaY = position.getY()
								- (device.getPosition().getY() + device
										.getSize().getHeight());
						double newWidth = device.getSize().getWidth() + deltaX;
						double newHeight = device.getSize().getHeight()
								+ deltaY;
						double scaleX = newWidth
								/ device.getInitSize().getWidth();
						double scaleY = newHeight
								/ device.getInitSize().getHeight();
						double newScale = 1;
						if (scaleX < scaleY)
							newScale = scaleX;
						else
							newScale = scaleY;
						if (newScale < 0.2)
							device.setScale(0.2);
						else if (newScale > 5)
							device.setScale(5);
						else
							device.setScale(newScale);
						break;
					}
					}
				}
				med.updatePerformed(null);
			}

		}
	}

	public void mouseReleased(MouseEvent e) {
		handleInMotion = null;
		med.startSelectState();
	}

}
