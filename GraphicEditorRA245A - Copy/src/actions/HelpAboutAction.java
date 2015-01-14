package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import dialogs.About;

public class HelpAboutAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3200484612037908122L;

	public HelpAboutAction() {

		putValue(
				ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK
						| ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/about.png"));
		putValue(NAME, "About");
		putValue(SHORT_DESCRIPTION, "About");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		About about = new About(MainFrame.getInstance(), "About", true);
		about.setVisible(true);

	}

}
