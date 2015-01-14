package states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import workspace.view.DiagramView;

public class LassoZoomState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3592055252599308384L;

	Rectangle2D rect = new Rectangle2D.Double();

	private DiagramView med;

	public LassoZoomState(DiagramView md) {
		med = md;
	}

	public void mouseDragged(MouseEvent e) {
		Point2D position = e.getPoint();
		med.transformToUserSpace(position);

		double width = position.getX() - med.getLastPosition().getX();
		double height = position.getY() - med.getLastPosition().getY();
		if ((width < 0) && (height < 0)) {
			rect.setRect(position.getX(), position.getY(), Math.abs(width),
					Math.abs(height));
		} else if ((width < 0) && (height >= 0)) {
			rect.setRect(position.getX(), med.getLastPosition().getY(),
					Math.abs(width), Math.abs(height));
		} else if ((width > 0) && (height < 0)) {
			rect.setRect(med.getLastPosition().getX(), position.getY(),
					Math.abs(width), Math.abs(height));
		} else {
			rect.setRect(med.getLastPosition().getX(), med.getLastPosition()
					.getY(), Math.abs(width), Math.abs(height));
		}
		med.setSelectionRectangle(rect);

		med.repaint();

	}

	public void mouseReleased(MouseEvent e) {
		// poziv metode koja će zumirati selektovani pravougaonik
		med.setSelectionRectangle(new Rectangle2D.Double(0, 0, 0, 0));
		med.repaint();

	}

}
