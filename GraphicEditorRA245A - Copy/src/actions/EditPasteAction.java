package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import workspace.view.DiagramView;

public class EditPasteAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3671729744717440364L;

	EditPasteAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		putValue(SMALL_ICON, loadIcon("../images/15/paste.png"));
		putValue(NAME, "Paste");
		putValue(SHORT_DESCRIPTION, "Paste");
	}
	
	public void actionPerformed(ActionEvent e) {
		((DiagramView)(MainFrame.getInstance().getDesktop().getSelectedFrame())).paste();
	}

}
