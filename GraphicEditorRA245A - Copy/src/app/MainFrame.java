package app;

import gui.Menu;
import gui.StatusBar;
import gui.Toolbar;
import gui.ToolsTBar;
import gui.WorkspaceTree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import windowListener.MyWindowListener;
import workspace.WorkspaceModel;
import workspace.view.DiagramView;
import actions.ActionManager;
import dialogs.ElementChangeDialog;

public class MainFrame extends JFrame implements ClipboardOwner {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8597474929275658305L;

	private static MainFrame instance = null;
	private JMenuBar menu;
	private Toolbar toolbar;
	private StatusBar statusBar;
	private ActionManager actionManager;
	private JDesktopPane desktop;
	private WorkspaceTree workspaceTree;
	private WorkspaceModel workspaceModel;

	private ToolsTBar toolsTBar;
	private ArrayList<DiagramView> diagramView = new ArrayList<DiagramView>();
	private ArrayList<ElementChangeDialog> dialogs = new ArrayList<ElementChangeDialog>();

	private Clipboard clipboard = new Clipboard("GrafEditor clipboard");

	public MainFrame() {

	}

	private void initialise() {
		actionManager = new ActionManager();

		initWorkspaceTree();
		initialiseGui();

	}

	private void initialiseGui() {

		/* +++++++++++++++ BASIC +++++++++++++++++ */
		URL iconURL = getClass().getResource("../images/logo2.png");
		setSize(1000, 760);
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		setTitle("Edittor RA245");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new MyWindowListener());

		// Import klasa
		menu = new Menu();
		setJMenuBar(menu);

		toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);

		statusBar = new StatusBar();
		add(statusBar, BorderLayout.SOUTH);

		toolsTBar = new ToolsTBar();
		add(toolsTBar, BorderLayout.EAST);

		desktop = new JDesktopPane();
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		/* +++++++++++++++++++++++++++++++++++++++ */

		/* +++++++++++++ SPLIT PANE ++++++++++++++ */
		/* +++++++++++++ TREE PANE +++++++++++++++ */
		JPanel panLeft = new JPanel();
		panLeft.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(workspaceTree);
		/* +++++++++++++++++++++++++++++++++++++++ */

		/* ++++++++++++ MAIN WINDOW ++++++++++++++ */
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				scrollPane, desktop);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(200);
		scrollPane.setMinimumSize(new Dimension(200, 0));
		add(splitPane, BorderLayout.CENTER);
		/* +++++++++++++++++++++++++++++++++++++++ */
	}

	private void initWorkspaceTree() {
		workspaceTree = new WorkspaceTree();

		workspaceModel = new WorkspaceModel();

		workspaceTree.setModel(workspaceModel);

	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialise();

		}
		return instance;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public JMenuBar getMenu() {
		return menu;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public JDesktopPane getDesktop() {
		return desktop;
	}

	public WorkspaceTree getWorkspaceTree() {
		return workspaceTree;
	}

	public WorkspaceModel getWorkspaceModel() {
		return workspaceModel;
	}

	public ArrayList<DiagramView> getDiagramView() {
		return diagramView;
	}

	public void setDiagramView(ArrayList<DiagramView> diagramView) {
		this.diagramView = diagramView;
	}

	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub

	}

	public Clipboard getClipboard() {
		return clipboard;
	}

	public ArrayList<ElementChangeDialog> getDialogs() {
		return dialogs;
	}

	public void setDialogs(ArrayList<ElementChangeDialog> dialogs) {
		this.dialogs = dialogs;
	}

	public void addDialog(ElementChangeDialog dialog) {
		getDialogs().add(dialog);
	}
	public void removeDialog(ElementChangeDialog dialog) {
		getDialogs().remove(dialog);
	}
}
