package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class EditUndoAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1673041630256431181L;

	EditUndoAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(SMALL_ICON, loadIcon("../images/15/undo.png"));
		putValue(NAME, "Undo");
		putValue(SHORT_DESCRIPTION, "Undo");
	}

	public void actionPerformed(ActionEvent e) {
		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();
		view.getCommandManager().undoCommand();

	}
}
