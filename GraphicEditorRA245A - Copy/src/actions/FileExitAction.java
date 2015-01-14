package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.KeyStroke;

import app.MainFrame;

public class FileExitAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileExitAction() {

		putValue(
				ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK
						| ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/exit.png"));
		putValue(NAME, "Exit");
		putValue(SHORT_DESCRIPTION, "Exit");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		MainFrame.getInstance().dispatchEvent(
				new WindowEvent(MainFrame.getInstance(),
						WindowEvent.WINDOW_CLOSING));
	}

}
