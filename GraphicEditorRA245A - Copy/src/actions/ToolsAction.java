package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import dialogs.Tools;

public class ToolsAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4742290838458185435L;

	public ToolsAction() {

		putValue(
				ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK
						| ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/tools.png"));
		putValue(NAME, "Tools");
		putValue(SHORT_DESCRIPTION, "Tools");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		toolsWindow();

	}

	public void toolsWindow() {
		Tools mit = new Tools(MainFrame.getInstance(), "Tools", false);

		mit.setVisible(true);

	}

}
