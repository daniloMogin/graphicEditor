package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;

public class WindowVertAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7614563682488055878L;

	private static int aveilableWidth;

	public WindowVertAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/Whorizontally.png"));
		putValue(NAME, "Tile Windows Vertically");
		putValue(SHORT_DESCRIPTION, "Tile Windows Vertically");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		tileWindowsVertically();

	}

	public void tileWindowsVertically() {

		if (MainFrame.getInstance().getDesktop().getAllFrames().length == 0)
			return;

		aveilableWidth = MainFrame.getInstance().getDesktop().getWidth()
				/ MainFrame.getInstance().getDesktop().getAllFrames().length;
		for (int i = 0; i < MainFrame.getInstance().getDesktop().getAllFrames().length; i++) {
			MainFrame.getInstance().getDesktop().getAllFrames()[i].setSize(
					aveilableWidth, MainFrame.getInstance().getDesktop()
							.getHeight());
			MainFrame.getInstance().getDesktop().getAllFrames()[i].setLocation(
					i * aveilableWidth, 0);
		}

	}

}
