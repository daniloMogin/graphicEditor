package workspace;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import app.MainFrame;
import events.UpdateEvent;
import events.UpdateListener;

public class Project implements TreeNode, UpdateListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3701281967651913182L;

	private ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
	private String name;
	private transient boolean changed;
	private Workspace workspace;
	private File projectFile;

	public Project(Workspace parent, String projectName) {
		this.name = projectName;
		this.workspace = parent;
		this.changed = false;
		this.projectFile = null;
	}

	public void addDiagram(Diagram diagram) {
		diagram.getModel().addUpdateListener(this);
		diagrams.add(diagram);
		diagram.setName(this.name + " - Diagram  - "
				+ String.valueOf(diagrams.size()) + " - Graphic Eeditor RA245 ");

	}

	public String toString() {
		return ((changed ? "* " : "") + name);
	}

	public Diagram getDiagram(int index) {
		return diagrams.get(index);
	}

	public int getDiagramIndex(Diagram diagram) {
		return diagrams.indexOf(diagram);
	}

	public int getDiagramCount() {
		return diagrams.size();
	}

	public boolean isLeaf() {
		return false;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getChildAt(int arg0) {
		return getDiagram(arg0);
	}

	public int getChildCount() {
		return getDiagramCount();
	}

	public TreeNode getParent() {
		return workspace;
	}

	public int getIndex(TreeNode arg0) {
		return getDiagramIndex((Diagram) arg0);
	}

	public boolean getAllowsChildren() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration children() {
		return (Enumeration) diagrams;
	}

	public ArrayList<Diagram> getDiagrams() {
		return diagrams;
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		setChanged(true);
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		if (this.changed != changed) {
			this.changed = changed;
			SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()
					.getWorkspaceTree());
		}
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

}
