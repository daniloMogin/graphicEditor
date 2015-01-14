package models.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import workspace.view.painters.TrianglePainter;

public class TriangleElement extends DiagramDevice {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9807970398156824L;

	public TriangleElement(Point2D position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		// TODO Auto-generated constructor stub
		elementPainter = new TrianglePainter(this);
	}

	public TriangleElement(TriangleElement triangle) {
		super(triangle);
		setName("triangle");
		elementPainter = new TrianglePainter(this);
	}

	public static DiagramDevice createDefault(Point2D pos, int eleNum) {
		Point2D position = (Point2D) pos.clone();

		Paint fill = Color.WHITE;

		TriangleElement triangleElement = new TriangleElement(position,
				new Dimension(40, 40), new BasicStroke((float) (2),
						BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND), fill,
				Color.BLACK);
		triangleElement.setName("Triangle: " + eleNum);

		return triangleElement;
	}

	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new TriangleElement(this);
	}

}
