package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.MainFrame;
import workspace.Diagram;
import workspace.Project;
import workspace.Workspace;

public class EditDeleteFromTreeAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub
		deleteAction();

	}

	public void deleteAction() {
		if (MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent() instanceof Workspace)
			return;

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
					if (MainFrame.getInstance().getDiagramView().get(i)
							.isSelected()) {
						try {
							MainFrame.getInstance().getDiagramView().get(i)
									.setClosed(true);
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MainFrame
								.getInstance()
								.getDiagramView()
								.remove(MainFrame.getInstance()
										.getDiagramView().get(i));
					}
					break;

				}

			}

			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
			return;

		}

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
						if (MainFrame.getInstance().getDiagramView().get(j)
								.getName().equals(pomDTN.getName()))
							pomIndex = j;
						break;
					}
				}

				if (pomIndex == -1)
					continue;

				if (MainFrame.getInstance().getDiagramView().get(pomIndex)
						.getName().equals(pomDTN.getName())) {
					if (!MainFrame.getInstance().getDiagramView().get(pomIndex)
							.isClosed()) {
						try {
							MainFrame.getInstance().getDiagramView()
									.get(pomIndex).setClosed(true);
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					MainFrame
							.getInstance()
							.getDiagramView()
							.remove(MainFrame.getInstance().getDiagramView()
									.get(pomIndex).getDiagram());
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
