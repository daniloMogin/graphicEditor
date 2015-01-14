package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class ViewZoomOutAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3733357922865958541L;

	public ViewZoomOutAction() {

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,
				ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/zoomOut.png"));
		putValue(NAME, "Zoom Out");
		putValue(SHORT_DESCRIPTION, "Zoom Out");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DiagramView) (MainFrame.getInstance().getDesktop().getSelectedFrame()))
				.zoomOut();

	}

}
