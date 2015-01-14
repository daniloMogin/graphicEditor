package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import models.DiagramElementsSelection;
import workspace.view.DiagramView;

public class EditCutAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6034442280733329580L;

	EditCutAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_T);
		putValue(SMALL_ICON, loadIcon("../images/15/cut.png"));
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cut");
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
			// nedostaje brisanje selektovanih elemenata sa dijagrama

		}
	}
}
