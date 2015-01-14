package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import workspace.Project;
import workspace.Workspace;
import app.MainFrame;

public class FileNewProjAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6275860965623052537L;

	public FileNewProjAction() {

		putValue(
				ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK
						| ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/newP.png"));
		putValue(NAME, "New Project");
		putValue(SHORT_DESCRIPTION, "New Project");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		fileNewProject();

	}

	public void fileNewProject() {
		Object x = MainFrame.getInstance().getWorkspaceModel().getRoot();
		Project p = new Project((Workspace) x, " ");
		MainFrame.getInstance().getWorkspaceTree().addProject(p);
		// Diagram d = new Diagram(" ");
		// p.addDiagram(d);
		//
		// DiagramView view = new DiagramView();
		// view.setDiagram(d);
		// MainFrame.getInstance().getDesktop().add(view);
		//
		// try {
		// view.setSelected(true);
		// } catch (PropertyVetoException exx) {
		// // TODO Auto-generated catch block
		// exx.printStackTrace();
		// }

	}

}
