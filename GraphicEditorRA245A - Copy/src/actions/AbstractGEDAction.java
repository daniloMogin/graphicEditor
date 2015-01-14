package actions;

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class AbstractGEDAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5368389941816382246L;

	/**
	 * Kreira ikonu na osnovu zadatog imena
	 * 
	 * @param fileName
	 * @return
	 */
	public Icon loadIcon(String fileName) {
		URL imageURL = getClass().getResource(fileName);
		Icon icon = null;

		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		} else {
			System.err.println("Resource not found: " + fileName);
		}

		return icon;
	}

}
