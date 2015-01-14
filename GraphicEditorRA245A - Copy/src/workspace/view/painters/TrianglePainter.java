package workspace.view.painters;

import java.awt.geom.GeneralPath;

import models.elements.DiagramElement;
import models.elements.TriangleElement;

public class TrianglePainter extends DevicePainter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1702860663531941560L;

	public TrianglePainter(DiagramElement device) {
		super(device);
		// TODO Auto-generated constructor stub
		TriangleElement triangle = (TriangleElement) device;

		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(0, 0 + 40);

		((GeneralPath) shape).lineTo(0 + triangle.getSize().width, 0 + 40);

		((GeneralPath) shape).lineTo(0 + triangle.getSize().width / 2,
				0 + 40 - triangle.getSize().height);

		((GeneralPath) shape).closePath();
	}

}
