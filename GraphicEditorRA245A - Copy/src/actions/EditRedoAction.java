package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class EditRedoAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7194381153651933001L;

	EditRedoAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(SMALL_ICON, loadIcon("../images/15/redo.png"));
		putValue(NAME, "Redo");
		putValue(SHORT_DESCRIPTION, "Redo");
	}

	public void actionPerformed(ActionEvent e) {
		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();
		view.getCommandManager().doCommand();

	}

}
