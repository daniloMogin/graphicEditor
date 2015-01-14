package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import models.DiagramElementsSelection;
import workspace.view.DiagramView;

public class EditCopyAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5639748094064497483L;

	EditCopyAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_C);
		putValue(SMALL_ICON, loadIcon("../images/15/copy.png"));
		putValue(NAME, "Copy");
		putValue(SHORT_DESCRIPTION, "Copy");
	}

	public void actionPerformed(ActionEvent e) {
		if (!(((DiagramView) (MainFrame.getInstance().getDesktop()
				.getSelectedFrame())).getDiagram().getSelectionModel()
				.getSelectionListSize() == 0)) {

			DiagramElementsSelection contents = new DiagramElementsSelection(
					((DiagramView) (MainFrame.getInstance().getDesktop()
							.getSelectedFrame())).getDiagram()
							.getSelectionModel().getSelected());
			MainFrame.getInstance().getClipboard()
					.setContents(contents, MainFrame.getInstance());
		}

	}
}
