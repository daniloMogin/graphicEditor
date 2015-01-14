package workspace;

import javax.swing.tree.DefaultTreeModel;

public class WorkspaceModel extends DefaultTreeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -375815249285411023L;

	public WorkspaceModel() {
		super(new Workspace());

	}

	public void addProject(Project project) {
		((Workspace) getRoot()).addProject(project);
	}

}
