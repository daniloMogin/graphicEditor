package workspace.view;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import workspace.Diagram;
import workspace.Project;

public class WorkspaceTreeCellRendered extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7962488067937702860L;

	public WorkspaceTreeCellRendered() {

		// TODO Auto-generated constructor stub
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);

		if (value instanceof Diagram) {
			URL imageURL = getClass().getResource("images/tdiagram.gif");
			Icon icon = null;
			if (imageURL != null)
				icon = new ImageIcon(imageURL);
			setIcon(icon);

		} else if (value instanceof Project) {
			URL imageURL = getClass().getResource("images/tproject.gif");
			Icon icon = null;
			if (imageURL != null)
				icon = new ImageIcon(imageURL);
			setIcon(icon);

		}

		return this;
	}
}
