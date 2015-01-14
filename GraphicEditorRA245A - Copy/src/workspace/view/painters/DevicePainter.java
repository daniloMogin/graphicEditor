package workspace.view.painters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import models.elements.DiagramDevice;
import models.elements.DiagramElement;

public class DevicePainter extends ElementPainter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8845727753386290632L;

	public DevicePainter(DiagramElement device) {
		super(device);
	}

	@Override
	public void paint(Graphics2D g, DiagramElement element) {

		AffineTransform oldTransform = g.getTransform();
		// uzimamo device kome painter pripada
		DiagramDevice device = (DiagramDevice) element;

		g.translate(device.getPosition().getX(), device.getPosition().getY());
		g.rotate(device.getRotation(), device.getSize().getWidth() / 2, device
				.getSize().getHeight() / 2);
		g.scale(device.getScale(), device.getScale());

		g.setPaint(element.getStrokeColor());
		g.setStroke(element.getStroke());
		g.draw(getShape());

		g.setPaint(element.getPaint());
		g.fill(getShape());
		g.setColor(Color.BLACK);
		g.setFont(new Font("monospaced", Font.BOLD, 9));
		g.drawString(element.getName(), 0, 55);
		g.setTransform(oldTransform);
	}

	@Override
	public boolean isElementAt(Point pos) {
		DiagramDevice device = (DiagramDevice) element;
		Rectangle2D rect = new Rectangle2D.Double();
		rect.setRect(device.getPosition().getX(), device.getPosition().getY(),
				device.getSize().getWidth(), device.getSize().getHeight());
		return rect.contains(pos);
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

}
