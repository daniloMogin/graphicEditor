package workspace.view.painters;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

import models.elements.DiagramElement;

public abstract class ElementPainter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1330734235854632113L;

	protected Shape shape;
	/**
	 * Referenca na Element objekat kome painter pripada.
	 */
	protected DiagramElement element;

	public ElementPainter(DiagramElement element) {
		this.element = element;
	}

	public abstract void paint(Graphics2D g, DiagramElement element);

	public abstract boolean isElementAt(Point pos);

	public Shape getShape() {
		return shape;
	}

}
