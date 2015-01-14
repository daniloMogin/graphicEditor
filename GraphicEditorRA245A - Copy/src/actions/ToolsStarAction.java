package actions;

import java.awt.event.ActionEvent;

import app.MainFrame;
import workspace.view.DiagramView;

public class ToolsStarAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7482399613469284034L;

	public ToolsStarAction() {

		putValue(SMALL_ICON, loadIcon("../images/25/star.png"));
		putValue(NAME, "Draw Star");
		putValue(SHORT_DESCRIPTION, "Draw Star");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).startStarState(); 

	}

}
