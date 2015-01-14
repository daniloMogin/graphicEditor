package gui;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import workspace.Project;
import workspace.WorkspaceModel;
import workspace.view.WorkspaceTreeCellRendered;
import workspace.view.WorkspaceTreeEditor;
import controllers.WorkspaceTreeController;

public class WorkspaceTree extends JTree {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkspaceTree() {

		addTreeSelectionListener(new WorkspaceTreeController());
		addMouseListener(new WorkspaceTreeController());
		setCellEditor(new WorkspaceTreeEditor(this,
				new DefaultTreeCellRenderer()));
		setCellRenderer(new WorkspaceTreeCellRendered());
		setEditable(true);
	}

	/**
	 * Metoda za dodavanje novog projekta u workspace
	 * 
	 * @param project
	 */
	public void addProject(Project project) {
		((WorkspaceModel) getModel()).addProject(project);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public Project getCurrentProject() {
		TreePath path = getSelectionPath();
		for (int i = 0; i < path.getPathCount(); i++) {
			if (path.getPathComponent(i) instanceof Project) {
				return (Project) path.getPathComponent(i);
			}
		}
		return null;
	}

}
