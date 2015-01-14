package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;

import app.MainFrame;

public class WindowCasAction extends AbstractGEDAction {

	private static int clutter;
	private static int count;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WindowCasAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/Wcascade.png"));
		putValue(NAME, "Cascade Windows");
		putValue(SHORT_DESCRIPTION, "Cascade Windows");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		cascadeWindows();

	}

	public void cascadeWindows() {
		if (MainFrame.getInstance().getDesktop().getAllFrames().length == 0)
			return;

		clutter = 0;
		count = 0;

		for (int i = 0; i < MainFrame.getInstance().getDesktop().getAllFrames().length; i++) {

			if (count * 30 >= (MainFrame.getInstance().getDesktop().getHeight() - 150)) {
				count = 0;
				clutter++;
			}

			MainFrame.getInstance().getDesktop().getAllFrames()[i].setSize(400,
					400);
			MainFrame.getInstance().getDesktop().getAllFrames()[i].setLocation(
					count * 20 + 60 * clutter, count * 30);
			count++;
			// obrne selektovane
			try {
				MainFrame.getInstance().getDesktop().getAllFrames()[i]
						.setSelected(true);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
