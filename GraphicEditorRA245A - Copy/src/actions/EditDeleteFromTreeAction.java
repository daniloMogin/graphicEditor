package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import workspace.Diagram;
import workspace.Project;
import workspace.Workspace;
import app.MainFrame;

public class EditDeleteFromTreeAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4753982775593231911L;

	public EditDeleteFromTreeAction() {

		putValue(
				ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK
						| ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/deleteTr.png"));
		putValue(NAME, "Delete From Tree");
		putValue(SHORT_DESCRIPTION, "Delete From Tree");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		deleteAction();

	}

	public void deleteAction() {
		// WORKSPACENODE SELECTED
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Workspace)
			return;

		// DIJAGRAMNODE SELECTED
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Diagram) {

			int pom = ((Workspace) MainFrame.getInstance().getWorkspaceModel()
					.getRoot()).getProjects().indexOf(
					((Diagram) MainFrame.getInstance().getWorkspaceTree()
							.getLastSelectedPathComponent()).getParent());

			((Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot())
					.getProjects()
					.get(pom)
					.getDiagrams()
					.remove(MainFrame.getInstance().getWorkspaceTree()
							.getLastSelectedPathComponent());

			for (int i = 0; i < MainFrame.getInstance().getDiagramView().size(); i++) {
				if (MainFrame
						.getInstance()
						.getDiagramView()
						.get(i)
						.getName()
						.equals(((Diagram) MainFrame.getInstance()
								.getWorkspaceTree()
								.getLastSelectedPathComponent()).getName())) {
					// if(MainFrame.getInstance().getDiagramFrame().get(i).isSelected()){
					MainFrame.getInstance().getDiagramView().get(i).dispose();
					MainFrame.getInstance().getDiagramView().remove(i);
					break;
					// }

				}
			}
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
			return;

		}

		// PROJECTNODE SELECTED
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Project) {

			int pom = ((Workspace) MainFrame.getInstance().getWorkspaceModel()
					.getRoot()).getProjects().indexOf(
					MainFrame.getInstance().getWorkspaceTree()
							.getLastSelectedPathComponent());
			Diagram pomDTN;
			int pomIndex = -1;

			for (int i = 0; i < ((Workspace) MainFrame.getInstance()
					.getWorkspaceModel().getRoot()).getProjects().get(pom)
					.getDiagrams().size(); i++) {
				pomIndex = -1;
				pomDTN = ((Workspace) MainFrame.getInstance()
						.getWorkspaceModel().getRoot()).getProjects().get(pom)
						.getDiagrams().get(i);
				for (int j = 0; j < MainFrame.getInstance().getDiagramView()
						.size(); j++) {
					if (MainFrame.getInstance().getDiagramView().get(j)
							.getName().equals(pomDTN.getName())) {
						pomIndex = j;
						break;
					}
				}

				if (pomIndex == -1)
					continue;

				if (MainFrame.getInstance().getDiagramView().get(pomIndex)
						.getName().equals(pomDTN.getName())) {
					if (!MainFrame.getInstance().getDiagramView().get(pomIndex)
							.isClosed())
						MainFrame.getInstance().getDiagramView().get(pomIndex)
								.dispose();
				}
			}

			((Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot())
					.getProjects().get(pom).getDiagrams().clear();
			((Workspace) MainFrame.getInstance().getWorkspaceModel().getRoot())
					.getProjects().remove(pom);

			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());

		}

	}
}
