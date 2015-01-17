package models.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import workspace.view.painters.StarPainter;

public class StarElement extends DiagramDevice {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3036920368558221865L;

	public StarElement(Point2D position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		// TODO Auto-generated constructor stub
		elementPainter = new StarPainter(this);
	}

	public StarElement(StarElement star) {
		super(star);
		setName("Kopija");
		elementPainter = new StarPainter(this);
	}

	public static DiagramDevice createDefault(Point2D pos, int eleNum) {
		Point2D position = (Point2D) pos.clone();

		Paint fill = Color.WHITE;

		StarElement starElement = new StarElement(position, new Dimension(40,
				40), new BasicStroke((float) (2), BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND), fill, Color.BLACK);

		starElement.setName("Star:" + eleNum);

		return starElement;

	}

	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new StarElement(this);
	}
}
