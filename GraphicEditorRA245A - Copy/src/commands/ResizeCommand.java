package commands;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import models.elements.DiagramDevice;
import models.elements.DiagramElement;
import workspace.view.DiagramView;

public class ResizeCommand extends AbstractCommand {

	DiagramView model;
	boolean justCameIn = false;
	ArrayList<DiagramDevice> list = new ArrayList<DiagramDevice>();

	ArrayList<Double> newScales = new ArrayList<Double>();
	ArrayList<Double> oldScales = new ArrayList<Double>();

	ArrayList<Point2D> oldPositions = new ArrayList<Point2D>();
	ArrayList<Point2D> newPositions = new ArrayList<Point2D>();

	public ResizeCommand(DiagramView model, ArrayList<DiagramElement> list,
			ArrayList<Double> oldScales, ArrayList<Point2D> oldPositions) {
		this.model = model;
		for (int i = 0; i < list.size(); i++)
			if (list.get(i) instanceof DiagramDevice) {
				this.list.add((DiagramDevice) list.get(i));

				this.newPositions.add(((DiagramDevice) list.get(i))
						.getPosition());
				this.oldPositions.add(oldPositions.get(0));

				this.newScales.add(((DiagramDevice) list.get(i)).getScale());
				this.oldScales.add(oldScales.get(0));

				oldPositions.remove(0);
				oldScales.remove(0);
			}
		justCameIn = true;

	}

	@Override
	public void doCommand() {
		if (!justCameIn)
			for (int i = 0; i < list.size(); i++) {
				DiagramDevice pom = (DiagramDevice) list.get(i);
				pom.setPosition(newPositions.get(i));
				pom.setScale(newScales.get(i));
			}
		justCameIn = false;
		model.getDiagram().getModel().fireUpdatePerformed();
		;
	}

	@Override
	public void undoCommand() {
		for (int i = 0; i < list.size(); i++) {
			DiagramDevice pom = (DiagramDevice) list.get(i);
			pom.setPosition(oldPositions.get(i));
			pom.setScale(oldScales.get(i));
		}
		model.getDiagram().getModel().fireUpdatePerformed();
	}

}
