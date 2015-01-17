package states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import workspace.view.DiagramView;

import commands.AddDeviceCommand;

public class CircleState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5048343146086387236L;

	private DiagramView med;

	public CircleState(DiagramView md) {
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
								DiagramView.CIRCLE));
			}

		}
	}
}
