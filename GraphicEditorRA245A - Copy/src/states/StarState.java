package states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import workspace.view.DiagramView;

import commands.AddDeviceCommand;

public class StarState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6342198922358382913L;

	private DiagramView med;

	public StarState(DiagramView md) {
		med = md;
	}

	public void mousePressed(MouseEvent e) {
		Point position = e.getPoint();
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (med.getDiagram().getModel().getElementAtPosition(position) == -1) {
				med.getCommandManager().addCommand(
						new AddDeviceCommand(med.getDiagram().getModel(), med
								.getDiagram().getSelectionModel(), position,
								DiagramView.STAR));
			}

		}
	}

}
