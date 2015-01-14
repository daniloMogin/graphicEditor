package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import app.MainFrame;

public class FileOpenWorkAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271448521370220619L;

	public FileOpenWorkAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/open.png"));
		putValue(NAME, "Open Workspace");
		putValue(SHORT_DESCRIPTION, "Open Workspace");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(MainFrame.getInstance());

	}

}
