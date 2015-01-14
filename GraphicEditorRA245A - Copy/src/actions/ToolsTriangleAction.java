package actions;

import java.awt.event.ActionEvent;

import workspace.view.DiagramView;
import app.MainFrame;

public class ToolsTriangleAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3088008256723811459L;

	public ToolsTriangleAction() {

		putValue(SMALL_ICON, loadIcon("../images/25/triangle.png"));
		putValue(NAME, "Draw Triangle");
		putValue(SHORT_DESCRIPTION, "Draw Triangle");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		((DiagramView) MainFrame.getInstance().getDesktop().getSelectedFrame())
				.startTriangleState();
	}

}
