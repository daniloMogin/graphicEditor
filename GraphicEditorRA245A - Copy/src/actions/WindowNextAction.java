package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class WindowNextAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -332796244934354280L;

	public WindowNextAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/WNext.png"));
		putValue(NAME, "Next Window");
		putValue(SHORT_DESCRIPTION, "Next Window");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		nextWindow();

	}

	@SuppressWarnings("unused")
	public void nextWindow() {
		if (MainFrame.getInstance().getDesktop().getAllFrames().length != 0) {
			DiagramView dv = (DiagramView) MainFrame.getInstance().getDesktop()
					.selectFrame(false);
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
