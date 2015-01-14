package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import workspace.Project;
import workspace.view.DiagramView;
import app.MainFrame;

public class FileOpenProjAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -495249492340764034L;

	public FileOpenProjAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/open.png"));
		putValue(NAME, "Open Project");
		putValue(SHORT_DESCRIPTION, "Open Project");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFileFilter());

		if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
			try {
				@SuppressWarnings("resource")
				ObjectInputStream os = new ObjectInputStream(
						new FileInputStream(jfc.getSelectedFile()));

				Project p = null;
				try {
					p = (Project) os.readObject();
				} catch (ClassNotFoundException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}

				MainFrame.getInstance().getWorkspaceTree().addProject(p);

				for (int i = 0; i < p.getDiagramCount(); i++) {
					DiagramView view = new DiagramView();
					p.getDiagram(i).getModel().addUpdateListener(p);
					view.setDiagram(p.getDiagram(i));

					MainFrame.getInstance().getDesktop().add(view);
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

}
