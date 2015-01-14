package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

public class ViewLassoZoomAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewLassoZoomAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/lassoZoom.png"));
		putValue(NAME, "Lasso Zoom");
		putValue(SHORT_DESCRIPTION, "Lasso Zoom");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
