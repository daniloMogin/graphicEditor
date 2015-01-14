package models;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.EventListenerList;

import models.elements.DiagramElement;
import events.UpdateEvent;
import events.UpdateListener;

public class DiagramModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	private String name;

	protected ArrayList<DiagramElement> diagramElements = new ArrayList<DiagramElement>();

	transient EventListenerList listenerList = new EventListenerList();
	UpdateEvent updateEvent = null;

	private Object readResolve() {
		listenerList = new EventListenerList();
		return this;
	}

	public int getElementAtPosition(Point point) {
		for (int i = getElementsCount() - 1; i >= 0; i--) {

			DiagramElement device = getElementAt(i);
			if (device.getPainter().isElementAt(point)) {
				return i;
			}

		}
		return -1;
	}

	public void removeElement(DiagramElement element) {

		diagramElements.remove(element);
		fireUpdatePerformed();
	}

	public int getElementsCount() {
		return diagramElements.size();
	}

	public DiagramElement getElementAt(int i) {
		return diagramElements.get(i);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public Iterator<DiagramElement> getElementsIterator() {
		return diagramElements.iterator();
	}

	public void addDiagramElement(DiagramElement element) {
		diagramElements.add(element);
		fireUpdatePerformed();
	}

	public void addUpdateListener(UpdateListener l) {
		listenerList.add(UpdateListener.class, l);
	}

	public void removeUpdateListener(UpdateListener l) {
		listenerList.remove(UpdateListener.class, l);
	}

	public ArrayList<DiagramElement> getDiagramElements() {
		return diagramElements;
	}

	/**
	 * Javljamo svim listenerima da se dogadjaj desio
	 */
	public void fireUpdatePerformed() {
		Object[] listeners = listenerList.getListenerList();
		for (int i = listeners.length - 1; i >= 0; i -= 1) {
			if (listeners[i] == UpdateListener.class) {
				if (updateEvent == null)
					updateEvent = new UpdateEvent(this);
				((UpdateListener) listeners[i + 1])
						.updatePerformed(updateEvent);
			}
		}
	}

}
