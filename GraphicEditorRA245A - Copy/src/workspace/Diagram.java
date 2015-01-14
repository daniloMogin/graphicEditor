package workspace;

import java.io.Serializable;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import models.DiagramModel;
import models.DiagramSelectionModel;

public class Diagram implements TreeNode, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8898869298625272545L;

	private String name;
	private Project parent;
	private DiagramModel model = new DiagramModel();

	private DiagramSelectionModel selectionModel;

	public Diagram(Project parent, String diagramName) {
		name = diagramName;
		this.parent = parent;

	}

	public DiagramModel getModel() {
		return model;
	}

	public void setModel(DiagramModel model) {
		this.model = model;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getChildAt(int arg0) {
		return null;
	}

	public int getChildCount() {
		return 0;
	}

	public TreeNode getParent() {
		return parent;
	}

	public int getIndex(TreeNode arg0) {
		return -1;
	}

	public boolean getAllowsChildren() {
		return false;
	}

	public boolean isLeaf() {
		return true;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration children() {
		return null;
	}

	public DiagramSelectionModel getSelectionModel() {
		if (selectionModel == null)
			selectionModel = new DiagramSelectionModel();
		return selectionModel;
	}

}
