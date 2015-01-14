/**
 * 
 */
package states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import workspace.view.DiagramView;

@SuppressWarnings("serial")
public class LassoState extends State {

	Rectangle2D rect = new Rectangle2D.Double();

	private DiagramView med;

	public LassoState(DiagramView md) {
		med = md;
	}

	public void mouseDragged(MouseEvent e) {
		Point2D mousePos = e.getPoint();
		med.transformToUserSpace(mousePos);

		if (!e.isControlDown()) {

			med.getDiagram().getSelectionModel().removeAllFromSelectionList();
		}

		double width = mousePos.getX() - med.getLastPosition().getX();
		double height = mousePos.getY() - med.getLastPosition().getY();
		if ((width < 0) && (height < 0)) {
			rect.setRect(mousePos.getX(), mousePos.getY(), Math.abs(width),
					Math.abs(height));
		} else if ((width < 0) && (height >= 0)) {
			rect.setRect(mousePos.getX(), med.getLastPosition().getY(),
					Math.abs(width), Math.abs(height));
		} else if ((width > 0) && (height < 0)) {
			rect.setRect(med.getLastPosition().getX(), mousePos.getY(),
					Math.abs(width), Math.abs(height));
		} else {
			rect.setRect(med.getLastPosition().getX(), med.getLastPosition()
					.getY(), Math.abs(width), Math.abs(height));
		}
		med.setSelectionRectangle(rect);

		med.getDiagram()
				.getSelectionModel()
				.selectElements(rect,
						med.getDiagram().getModel().getDiagramElements());
		med.setSelectionRectangle(rect);

		med.repaint();

	}

	public void mouseReleased(MouseEvent e) {
		med.setSelectionRectangle(new Rectangle2D.Double(0, 0, 0, 0));
		med.repaint();
		med.startSelectState();
	}

}
