package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JToolBar;

import app.MainFrame;

public class ToolsTBar extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2265762358252446382L;

	public ToolsTBar() {

		/**
		 * Dodavanje akcija u tools Toolbar
		 * 
		 * @param toolsBar
		 */

		/* ++++++++++++++ TOOLBAR ++++++++++++++++ */
		setBackground(new Color(232, 205, 7));
		setPreferredSize(new Dimension(50, 50));
		setOrientation(VERTICAL);
		setLayout(new FlowLayout());

		add(MainFrame.getInstance().getActionManager().getToolsSelectAction());
		add(MainFrame.getInstance().getActionManager()
				.getToolsRectangleAction());
		add(MainFrame.getInstance().getActionManager().getToolsCircleAction());
		add(MainFrame.getInstance().getActionManager().getToolsTriangleAction());
		add(MainFrame.getInstance().getActionManager().getToolsStarAction());

	}

}
