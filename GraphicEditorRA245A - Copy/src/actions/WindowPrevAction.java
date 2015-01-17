package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class WindowPrevAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7305172825723228257L;

	public WindowPrevAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/WPrev.png"));
		putValue(NAME, "Previous Window");
		putValue(SHORT_DESCRIPTION, "Previous Window");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		prevWindow();

	}

	@SuppressWarnings("unused")
	public void prevWindow() {
		if (MainFrame.getInstance().getDesktop().getAllFrames().length != 0) {
			DiagramView dv = (DiagramView) MainFrame.getInstance().getDesktop()
					.selectFrame(true);
			if (!dv.isVisible()) {
				try {
					dv.setVisible(true);
					dv.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				dv.setVisible(false);
			}

			if (dv == null) {
				return;
			}
			try {
				dv.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}

	}

}
