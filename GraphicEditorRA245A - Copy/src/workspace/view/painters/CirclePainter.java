package workspace.view.painters;

import java.awt.geom.Ellipse2D;

import models.elements.CircleElement;
import models.elements.DiagramElement;

public class CirclePainter extends DevicePainter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3073026532763893686L;

	public CirclePainter(DiagramElement device) {
		super(device);
		CircleElement circleEl = (CircleElement) device;

		shape = new Ellipse2D.Double(0, 0, circleEl.getSize().getWidth(),
				circleEl.getSize().getHeight());
	}

}
