package workspace.view.painters;

import java.awt.geom.Rectangle2D;

import models.elements.DiagramElement;
import models.elements.RectangleElement;

public class RectanglePainter extends DevicePainter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2987491186689538683L;

	public RectanglePainter(DiagramElement device) {
		super(device);

		RectangleElement rectangle = (RectangleElement) device;

		shape = new Rectangle2D.Double(0, 0, rectangle.getSize().getWidth(),
				rectangle.getSize().getHeight());
	}

}
