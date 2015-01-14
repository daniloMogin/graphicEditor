package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import workspace.Project;
import app.MainFrame;

public class FileSaveAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2772954487335944696L;

	public FileSaveAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/save.png"));
		putValue(NAME, "Save");
		putValue(SHORT_DESCRIPTION, "Save");

	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new DiagramFileFilter());

		Project project = MainFrame.getInstance().getWorkspaceTree()
				.getCurrentProject();
		File projectFile = project.getProjectFile();

		if (!project.isChanged()) {
			return;
		}

		if (project.getProjectFile() == null) {
			if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
				projectFile = jfc.getSelectedFile();

			} else {
				return;
			}

		}

		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			os.writeObject(project);
			project.setProjectFile(projectFile);
			project.setChanged(false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
