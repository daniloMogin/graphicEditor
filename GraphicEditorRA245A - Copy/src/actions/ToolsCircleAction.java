package actions;

import java.awt.event.ActionEvent;

import app.MainFrame;
import workspace.view.DiagramView;

public class ToolsCircleAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7482399613469284034L;

	public ToolsCircleAction() {

		putValue(SMALL_ICON, loadIcon("../images/25/circle.png"));
		putValue(NAME, "Draw Circle");
		putValue(SHORT_DESCRIPTION, "Draw Circle");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).startCircleState();

	}

}
