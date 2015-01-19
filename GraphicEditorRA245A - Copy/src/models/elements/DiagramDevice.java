package models.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public abstract class DiagramDevice extends DiagramElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9214389939302780372L;

	protected Dimension size;
	protected Point2D position;
	protected double scale;
	protected double rotation;

	public DiagramDevice(Point2D position, Dimension size, Stroke stroke,
			Paint paint, Color strokeColor) {
		super(stroke, paint, strokeColor);
		this.size = size;
		// ovo omogućava translaciju tačaka tako da se element kreira u centru
		position.setLocation(position.getX() - size.getWidth() / 2,
				position.getY() - size.getHeight() / 2);
		this.position = position;
		this.strokeColor = strokeColor;
		this.scale = 1;
		this.rotation = 0;
	}

	/*
	 * Copy konstruktor
	 */
	public DiagramDevice(DiagramDevice device) {
		super(device);
		this.size = device.size;
		this.position = device.position;
		this.scale = device.getScale();

	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Dimension getInitSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		if (Math.abs(rotation - 2 * Math.PI) < 0.01
				|| Math.abs(rotation + 2 * Math.PI) < 0.01)
			rotation = 0;
		this.rotation = rotation;
	}

	public Dimension getSize() {
		Dimension tempSize = new Dimension();
		tempSize.setSize(size.getWidth() * getScale(), size.getHeight()
				* getScale());
		return tempSize;
	}

}
