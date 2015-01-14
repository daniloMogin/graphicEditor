package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;

public class WindowHorAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int aveilableHeight;

	public WindowHorAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/Wvertical.png"));
		putValue(NAME, "Tile Windows Horizontally");
		putValue(SHORT_DESCRIPTION, "Tile Windows Horizontally");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		tileWindowsHorizontally();

	}

	public void tileWindowsHorizontally() {

		if (MainFrame.getInstance().getDesktop().getAllFrames().length == 0)
			return;

		aveilableHeight = MainFrame.getInstance().getDesktop().getHeight()
				/ MainFrame.getInstance().getDesktop().getAllFrames().length;

		for (int i = 0; i < MainFrame.getInstance().getDesktop().getAllFrames().length; i++) {
			MainFrame.getInstance().getDesktop().getAllFrames()[i].setSize(
					MainFrame.getInstance().getDesktop().getWidth(),
					aveilableHeight);
			MainFrame.getInstance().getDesktop().getAllFrames()[i].setLocation(
					0, i * aveilableHeight);
		}

	}

}
