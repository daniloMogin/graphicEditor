package commands;

import models.elements.DiagramDevice;
import workspace.view.DiagramView;

public class RotateCommand extends AbstractCommand {

	DiagramView model;

	DiagramDevice device = null;

	double newRotation = 0;
	double oldRotation = 0;

	public RotateCommand(DiagramView model, DiagramDevice device,
			double newRotation) {
		this.model = model;
		this.device = device;
		this.newRotation = device.getRotation() + newRotation;
		this.oldRotation = device.getRotation();

	}

	@Override
	public void doCommand() {
		device.setRotation(newRotation);
		model.getDiagram().getModel().fireUpdatePerformed();
	}

	@Override
	public void undoCommand() {
		device.setRotation(oldRotation);
		model.getDiagram().getModel().fireUpdatePerformed();
	}

}
