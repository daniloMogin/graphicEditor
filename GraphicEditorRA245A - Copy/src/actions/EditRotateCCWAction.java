package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import models.DiagramSelectionModel;
import models.elements.DiagramDevice;
import workspace.view.DiagramView;
import app.MainFrame;

import commands.RotateCommand;

public class EditRotateCCWAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8844232992775782542L;

	public EditRotateCCWAction() {

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_OPEN_BRACKET, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/rotateCCW.png"));
		putValue(NAME, "Rotate Counterclockwise");
		putValue(SHORT_DESCRIPTION, "Rotate Counterclockwise");

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
										-Math.PI / 2));

		((DiagramView) (MainFrame.getInstance().getDesktop().getSelectedFrame()))
				.updatePerformed(null);

	}

}
