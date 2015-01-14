package models.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import workspace.view.painters.RectanglePainter;

public class RectangleElement extends DiagramDevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;

	public RectangleElement(Point2D position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new RectanglePainter(this);
	}

	public RectangleElement(RectangleElement rectangle) {
		super(rectangle);
		setName("rectangle");
		elementPainter = new RectanglePainter(this);
	}

	public static DiagramDevice createDefault(Point2D pos, int eleNum) {
		Point2D position = (Point2D) pos.clone();

		Paint fill = Color.WHITE;

		RectangleElement rectangleElement = new RectangleElement(position,
				new Dimension(80, 40), new BasicStroke((float) (2),
						BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL), fill,
				Color.BLACK);
		rectangleElement.setName("Rectangle " + eleNum);
		return rectangleElement;
	}

	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new RectangleElement(this);
	}

}
