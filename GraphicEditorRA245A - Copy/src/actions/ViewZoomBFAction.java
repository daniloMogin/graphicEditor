package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class ViewZoomBFAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5300753184412687332L;

	public ViewZoomBFAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/zoomBF.png"));
		putValue(NAME, "Zoom to Best Fit");
		putValue(SHORT_DESCRIPTION, "Zoom to Best Fit");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.bestFitZoom();

	}

}
