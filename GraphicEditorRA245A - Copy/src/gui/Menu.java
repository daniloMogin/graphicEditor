package gui;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import app.MainFrame;

public class Menu extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7863806568418316202L;

	public Menu() {

		/**
		 * Dodavanje akcija u Meni
		 * 
		 * @param menu
		 */
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileOpenProjAction());
		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileOpenWorkAction());
		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileNewProjAction());
		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileNewDiagAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileSaveAction());
		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileSaveAsAction());
		fileMenu.addSeparator();
		fileMenu.add(MainFrame.getInstance().getActionManager()
				.getFileExitAction());

		add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);

		editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
		editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
		editMenu.addSeparator();
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getToolsAction());
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getSearchAction());
		editMenu.addSeparator();
		editMenu.add(MainFrame.getInstance().getActionManager().getCutAction());
		editMenu.add(MainFrame.getInstance().getActionManager().getCopyAction());
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getPasteAction());
		editMenu.addSeparator();
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getRotateCCWAction());
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getRotateCWAction());
		editMenu.addSeparator();
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getDeleteElementAction());
		editMenu.add(MainFrame.getInstance().getActionManager()
				.getDeleteFromTreeAction());

		add(editMenu);

		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_I);

		viewMenu.add(MainFrame.getInstance().getActionManager()
				.getViewZoomInAction());
		viewMenu.add(MainFrame.getInstance().getActionManager()
				.getViewZoomOutAction());
		viewMenu.addSeparator();
		viewMenu.add(MainFrame.getInstance().getActionManager()
				.getViewLassoZoomAction());
		viewMenu.add(MainFrame.getInstance().getActionManager()
				.getViewZoomBFAction());

		add(viewMenu);

		JMenu selectMenu = new JMenu("Select");
		selectMenu.setMnemonic(KeyEvent.VK_S);

		selectMenu.add(MainFrame.getInstance().getActionManager()
				.getSelectAllAction());

		add(selectMenu);

		JMenu windowMenu = new JMenu("Window");
		windowMenu.setMnemonic(KeyEvent.VK_W);

		windowMenu.add(MainFrame.getInstance().getActionManager()
				.getWindowsCasAction());
		windowMenu.add(MainFrame.getInstance().getActionManager()
				.getWindowHorAction());
		windowMenu.add(MainFrame.getInstance().getActionManager()
				.getWindowVertAction());
		windowMenu.addSeparator();
		windowMenu.add(MainFrame.getInstance().getActionManager()
				.getWindowNextAction());
		windowMenu.add(MainFrame.getInstance().getActionManager()
				.getWindowPrevAction());

		add(windowMenu);

		add(Box.createHorizontalGlue());

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_L);

		helpMenu.add(MainFrame.getInstance().getActionManager()
				.getHelpAboutAction());

		add(helpMenu);

	}

}
