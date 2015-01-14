package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import app.MainFrame;

public class Toolbar extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1209699209668701828L;
	
	private UIManager.LookAndFeelInfo[] laf;
	private JComboBox jcbLaf;

	public Toolbar() {

		/**
		 * Dodavanje akcija u toolbar
		 * 
		 * @param toolbar
		 */

		setFloatable(false);

		add(MainFrame.getInstance().getActionManager().getFileOpenProjAction());
		add(MainFrame.getInstance().getActionManager().getFileOpenWorkAction());
		add(MainFrame.getInstance().getActionManager().getFileNewProjAction());
		add(MainFrame.getInstance().getActionManager().getFileNewDiagAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getFileSaveAction());
		add(MainFrame.getInstance().getActionManager().getFileSaveAsAction());
		addSeparator();

		add(MainFrame.getInstance().getActionManager().getUndoAction());
		add(MainFrame.getInstance().getActionManager().getRedoAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getSearchAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getCutAction());
		add(MainFrame.getInstance().getActionManager().getCopyAction());
		add(MainFrame.getInstance().getActionManager().getPasteAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getRotateCCWAction());
		add(MainFrame.getInstance().getActionManager().getRotateCWAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
		add(MainFrame.getInstance().getActionManager()
				.getDeleteFromTreeAction());
		addSeparator();

		add(MainFrame.getInstance().getActionManager().getViewZoomInAction());
		add(MainFrame.getInstance().getActionManager().getViewZoomOutAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getViewLassoZoomAction());
		add(MainFrame.getInstance().getActionManager().getViewZoomBFAction());
		addSeparator();

		add(MainFrame.getInstance().getActionManager().getSelectAllAction());
		addSeparator();

		add(MainFrame.getInstance().getActionManager().getWindowsCasAction());
		add(MainFrame.getInstance().getActionManager().getWindowHorAction());
		add(MainFrame.getInstance().getActionManager().getWindowVertAction());
		addSeparator();
		add(MainFrame.getInstance().getActionManager().getWindowNextAction());
		add(MainFrame.getInstance().getActionManager().getWindowPrevAction());
		addSeparator();

		add(Box.createHorizontalGlue());
		add(MainFrame.getInstance().getActionManager().getToolsAction());

		/**
		 * Event koji nam omogucava da promenimo "look and feel" iz padajuce
		 * liste na toolbaru
		 * 
		 * @param toolbar
		 */

		laf = UIManager.getInstalledLookAndFeels();
		String[] LAFNames = new String[laf.length];
		for (int i = 0; i < laf.length; i++) {
			LAFNames[i] = laf[i].getName();
		}

		jcbLaf = new JComboBox(LAFNames);
		jcbLaf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					UIManager.setLookAndFeel(laf[jcbLaf.getSelectedIndex()]
							.getClassName());
					SwingUtilities.updateComponentTreeUI(MainFrame
							.getInstance());
				} catch (Exception ex) {
					System.out.println("Could not load "
							+ laf[jcbLaf.getSelectedIndex()].getClassName());
				}

			}
		});
		add(jcbLaf);
	}
}
