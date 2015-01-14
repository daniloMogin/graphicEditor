package models.elements;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.io.Serializable;

import serialization.SerializableStrokeAdapter;
import workspace.view.painters.ElementPainter;

public abstract class DiagramElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8461465775205447183L;

	protected Paint paint;
	protected SerializableStrokeAdapter stroke;
	protected Color strokeColor;

	protected String name;
	protected String description;

	protected ElementPainter elementPainter;

	// metoda za kloniranje elemenata
	abstract public DiagramElement clone();

	public DiagramElement(Stroke stroke, Paint paint, Color strokeColor) {
		setStroke(stroke);
		this.paint = paint;
		this.strokeColor = strokeColor;
	}

	/**
	 * Copy konstruktor klase
	 * 
	 * 
	 */
	public DiagramElement(DiagramElement element) {
		this.stroke = element.stroke;
		this.paint = element.paint;
		this.strokeColor = element.strokeColor;
		this.name = element.name;
		this.description = element.description;
		this.elementPainter = element.elementPainter;
	}

	public ElementPainter getElementPainter() {
		return elementPainter;
	}

	public void setElementPainter(ElementPainter elementPainter) {
		this.elementPainter = elementPainter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ElementPainter getPainter() {
		return elementPainter;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = new SerializableStrokeAdapter(stroke);
	}

	public String toString() {
		return name;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}

}
