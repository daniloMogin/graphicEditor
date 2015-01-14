package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import app.MainFrame;

public class FileSaveAsAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7115228777839903945L;

	public FileSaveAsAction() {

		putValue(
				ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK
						| ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/saveAs.png"));
		putValue(NAME, "Save As");
		putValue(SHORT_DESCRIPTION, "Save As");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser();
		jfc.showSaveDialog(MainFrame.getInstance());

	}

}
