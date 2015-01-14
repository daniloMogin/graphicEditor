package commands;

import java.awt.geom.Point2D;

import models.DiagramModel;
import models.DiagramSelectionModel;
import models.elements.CircleElement;
import models.elements.DiagramElement;
import models.elements.RectangleElement;
import models.elements.StarElement;
import models.elements.TriangleElement;
import workspace.view.DiagramView;

public class AddDeviceCommand extends AbstractCommand {

	DiagramModel model;
	Point2D lastPosition;
	DiagramElement device = null;
	DiagramSelectionModel selectionModel;
	int deviceType;

	public AddDeviceCommand(DiagramModel model,
			DiagramSelectionModel selectionModel, Point2D lastPosition,
			int deviceType) {
		this.model = model;
		this.lastPosition = lastPosition;
		this.selectionModel = selectionModel;
		this.deviceType = deviceType;

	}

	@Override
	public void doCommand() {
		if (device == null)
			if (deviceType == DiagramView.CIRCLE) {
				device = CircleElement.createDefault(lastPosition,
						model.getElementsCount());
			} else if (deviceType == DiagramView.RECTANGLE) {
				device = RectangleElement.createDefault(lastPosition,
						model.getElementsCount());
			} else if (deviceType == DiagramView.TRIANGLE) {
				device = TriangleElement.createDefault(lastPosition,
						model.getElementsCount());
			} else if (deviceType == DiagramView.STAR) {
				device = StarElement.createDefault(lastPosition,
						model.getElementsCount());
			}

		selectionModel.removeAllFromSelectionList();
		model.addDiagramElement(device);
		selectionModel.addToSelectionList(device);

	}

	@Override
	public void undoCommand() {
		selectionModel.removeAllFromSelectionList();
		model.removeElement(device);

	}

}
