package states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import workspace.view.DiagramView;

import commands.AddDeviceCommand;

public class TriangleState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3394300731382029322L;

	private DiagramView med;

	public TriangleState(DiagramView md) {
		med = md;
	}

	public void mousePressed(MouseEvent e) {
		Point position = e.getPoint();
		med.transformToUserSpace(position);
		
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (med.getDiagram().getModel().getElementAtPosition(position) == -1) {
				med.getCommandManager().addCommand(
						new AddDeviceCommand(med.getDiagram().getModel(), med
								.getDiagram().getSelectionModel(), position,
								DiagramView.TRIANGLE));
			}

		}
	}
}
