package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import models.DiagramSelectionModel;
import models.elements.DiagramDevice;
import workspace.view.DiagramView;
import app.MainFrame;

import commands.RotateCommand;

public class EditRotateCWAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5432419093595710906L;

	public EditRotateCWAction() {

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_CLOSE_BRACKET, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/rotateCW.png"));
		putValue(NAME, "Rotate Clockwise");
		putValue(SHORT_DESCRIPTION, "Rotate Clockwise");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (MainFrame.getActiveDiagram() == null)
			return;

		DiagramSelectionModel selectionModel = MainFrame.getActiveDiagram()
				.getDiagram().getSelectionModel();

		if (!(selectionModel.getSelectionList().size() != 1))
			if (selectionModel.getSelectionList().get(0) instanceof DiagramDevice)
				MainFrame
						.getActiveDiagram()
						.getCommandManager()
						.addCommand(
								new RotateCommand(MainFrame.getActiveDiagram(),
										(DiagramDevice) selectionModel
												.getSelectionList().get(0),
										+Math.PI / 2));

		((DiagramView) (MainFrame.getInstance().getDesktop().getSelectedFrame()))
				.updatePerformed(null);

	}

}
