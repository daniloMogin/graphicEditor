package workspace.view.painters;

import java.awt.geom.GeneralPath;

import models.elements.DiagramElement;
import models.elements.StarElement;

public class StarPainter extends DevicePainter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9119211413414248015L;

	public StarPainter(DiagramElement device) {
		super(device);
		// TODO Auto-generated constructor stub
		StarElement star = (StarElement) device;

		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(0, 0 + 18);

		((GeneralPath) shape).lineTo(0 + star.getSize().width, 0 + 18);

		((GeneralPath) shape).lineTo(0 + star.getSize().width / 4,
				0 + 18 + star.getSize().height / 2);

		((GeneralPath) shape).lineTo(0 + star.getSize().width / 2,
				0 + 18 - star.getSize().height / 2.5);

		((GeneralPath) shape).lineTo(0 + star.getSize().width / 1.3,
				0 + 18 + star.getSize().height / 2);

		((GeneralPath) shape).closePath();
	}

}
