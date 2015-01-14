package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import workspace.Diagram;
import workspace.Workspace;
import workspace.view.DiagramView;
import app.MainFrame;

public class WorkspaceTreeController implements TreeSelectionListener,
		MouseListener {

	private Diagram diag;
	private DiagramView diagView;

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

		TreePath path = e.getPath();
		/**
		 * provera sta je selektovano ako nije Project, ikona za new diag je
		 * iskljucena
		 */
		if (path.getLastPathComponent() instanceof Diagram
				|| path.getLastPathComponent() instanceof Workspace) {
			MainFrame.getInstance().getActionManager().getFileNewDiagAction()
					.setEnabled(false);

		} else {
			MainFrame.getInstance().getActionManager().getFileNewDiagAction()
					.setEnabled(true);

		}

		/**
		 * prolazak kroz kolekciju dijagrama i provera koji diag ce doci u fokus
		 */
		for (int i = 0; i < path.getPathCount(); i++) {
			if (path.getPathComponent(i) instanceof Diagram) {
				diag = (Diagram) path.getPathComponent(i);
				String dname = diag.getName();

				JInternalFrame[] allframes = MainFrame.getInstance()
						.getDesktop().getAllFrames();

				for (int j = 0; j < allframes.length; j++) {
					if (dname == allframes[j].getName()) {
						try {
							allframes[j].setSelected(true);
							allframes[j].setVisible(true);
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					}
				}

				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {

			if (MainFrame.getInstance().getWorkspaceTree()
					.getLastSelectedPathComponent() instanceof Diagram) {
				Diagram diag1 = (Diagram) MainFrame.getInstance()
						.getWorkspaceTree().getLastSelectedPathComponent();
				for (int i = 0; i < MainFrame.getInstance().getDesktop()
						.getAllFrames().length; i++) {

					if (MainFrame
							.getInstance()
							.getWorkspaceTree()
							.getLastSelectedPathComponent()
							.toString()
							.equals(MainFrame.getInstance().getDesktop()
									.getAllFrames()[i].getName())) {
						return;
					}

				}

				diagView = new DiagramView();
				diagView.setTitle(diag1.getName());
				diagView.setName(diag1.getName());
				diagView.setDiagram(diag1);
				MainFrame.getInstance().getDesktop().add(diagView);

				try {
					diagView.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// System.out.println(sel);
			}

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
