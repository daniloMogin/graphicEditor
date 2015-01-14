package models.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import workspace.view.painters.CirclePainter;

public class CircleElement extends DiagramDevice {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;

	public CircleElement(Point2D position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);

		elementPainter = new CirclePainter(this);

	}

	public CircleElement(CircleElement circle) {
		super(circle);
		setName("kopija");
		elementPainter = new CirclePainter(this);
	}

	public static DiagramDevice createDefault(Point2D pos, int elemNo) {
		Point2D position = (Point2D) pos.clone();

		Paint fill = Color.WHITE;
		CircleElement circEl = new CircleElement(position, new Dimension(80, 40),
				new BasicStroke((float) (2), BasicStroke.CAP_SQUARE,
						BasicStroke.JOIN_BEVEL), fill, Color.BLACK);
		circEl.setName("Circle " + elemNo);
		return circEl;
	}

	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new CircleElement(this);
	}

}
