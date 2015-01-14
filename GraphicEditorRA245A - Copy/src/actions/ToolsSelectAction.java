package actions;

import java.awt.event.ActionEvent;

import app.MainFrame;
import workspace.view.DiagramView;

public class ToolsSelectAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996967152212800119L;

	public ToolsSelectAction() {

		putValue(SMALL_ICON, loadIcon("../images/25/selectJ.png"));
		putValue(NAME, "Select");
		putValue(SHORT_DESCRIPTION, "Select");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.startSelectState();
	}

}
