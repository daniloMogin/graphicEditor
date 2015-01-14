package actions;

import java.awt.event.ActionEvent;

import app.MainFrame;
import workspace.view.DiagramView;

public class ToolsRectangleAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7895253552686288589L;

	public ToolsRectangleAction() {

		putValue(SMALL_ICON, loadIcon("../images/25/rectangle.png"));
		putValue(NAME, "Draw Rectangle");
		putValue(SHORT_DESCRIPTION, "Draw Rectangle");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView)MainFrame.getInstance().getDesktop().getSelectedFrame()).startRectangleState();

	}

}
