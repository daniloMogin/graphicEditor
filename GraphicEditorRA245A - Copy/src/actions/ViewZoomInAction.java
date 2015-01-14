package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import workspace.view.DiagramView;
import app.MainFrame;

public class ViewZoomInAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 282385124935169684L;

	public ViewZoomInAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/zoomIn.png"));
		putValue(NAME, "Zoom In");
		putValue(SHORT_DESCRIPTION, "Zoom In");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((DiagramView) (MainFrame.getInstance().getDesktop().getSelectedFrame()))
				.zoomIn();

	}

}
