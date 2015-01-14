package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import workspace.view.DiagramView;

public class ViewZoomBFAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewZoomBFAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/zoomBF.png"));
		putValue(NAME, "Zoom to Best Fit");
		putValue(SHORT_DESCRIPTION, "Zoom to Best Fit");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).bestFitZoom();

	}

}
