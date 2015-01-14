package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import app.MainFrame;
import workspace.Diagram;
import workspace.Project;
import workspace.view.DiagramView;

public class FileNewDiagAction extends AbstractGEDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileNewDiagAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/newD.png"));
		putValue(NAME, "New Diagram");
		putValue(SHORT_DESCRIPTION, "New Diagram");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object p = MainFrame.getInstance().getWorkspaceTree()
				.getLastSelectedPathComponent();

		if (p instanceof Project) {
			Diagram d = new Diagram((Project)p,"New diagram");
			((Project) p).addDiagram(d);
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());

			DiagramView view = new DiagramView();
			view.setDiagram(d);
			MainFrame.getInstance().getDesktop().add(view);

			try {
				view.setSelected(true);
			} catch (PropertyVetoException exx) {
				exx.printStackTrace();
			}

		}

	}

}
