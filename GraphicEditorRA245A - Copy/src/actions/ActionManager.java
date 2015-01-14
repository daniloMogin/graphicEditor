package actions;

public class ActionManager {

	/* ++++++++++++++++ File +++++++++++++++++ */
	private FileNewDiagAction fileNewDiagAction;
	private FileNewProjAction fileNewProjAction;
	private FileOpenProjAction fileOpenProjAction;
	private FileOpenWorkAction fileOpenWorkAction;
	private FileSaveAction fileSaveAction;
	private FileSaveAsAction fileSaveAsAction;
	private FileExitAction fileExitAction;
	/* +++++++++++++++++++++++++++++++++++++++ */

	/* ++++++++++++++++ EDIT +++++++++++++++++ */
	private ToolsAction toolsAction;
	private EditUndoAction undoAction;
	private EditRedoAction redoAction;
	private EditDeleteElementAction deleteElementAction;
	private EditDeleteFromTreeAction deleteFromTreeAction;
	private EditRotateCCWAction rotateCCWAction;
	private EditRotateCWAction rotateCWAction;
	private SearchAction searchAction;
	private EditCutAction cutAction;
	private EditCopyAction copyAction;
	private EditPasteAction pasteAction;
	/* +++++++++++++++++++++++++++++++++++++++ */

	/* ++++++++++++++++ VIEW +++++++++++++++++ */
	private ViewZoomInAction viewZoomInAction;
	private ViewZoomOutAction viewZoomOutAction;
	private ViewLassoZoomAction viewLassoZoomAction;
	private ViewZoomBFAction viewZoomBFAction;
	/* +++++++++++++++++++++++++++++++++++++++ */

	/* +++++++++++++++ SELECT ++++++++++++++++ */
	private SelectAllAction selectAllAction;
	/* +++++++++++++++++++++++++++++++++++++++ */

	/* +++++++++++++++ WINDOW ++++++++++++++++ */
	private WindowCasAction windowsCasAction;
	private WindowVertAction windowVertAction;
	private WindowHorAction windowHorAction;
	private WindowPrevAction windowPrevAction;
	private WindowNextAction windowNextAction;
	/* +++++++++++++++++++++++++++++++++++++++ */

	/* ++++++++++++++++ HELP +++++++++++++++++ */
	private HelpAboutAction helpAboutAction;
	/* +++++++++++++++++++++++++++++++++++++++ */

	/* +++++++++++++++ TOOLS +++++++++++++++++ */
	private ToolsCircleAction toolsCircleAction;
	private ToolsRectangleAction toolsRectangleAction;
	private ToolsSelectAction toolsSelectAction;
	private ToolsTriangleAction toolsTriangleAction;
	private ToolsStarAction toolsStarAction;

	/* +++++++++++++++++++++++++++++++++++++++ */

	public ActionManager() {

		initialiseActions();

	}

	private void initialiseActions() {

		fileNewDiagAction = new FileNewDiagAction();
		fileNewProjAction = new FileNewProjAction();
		fileOpenProjAction = new FileOpenProjAction();
		fileOpenWorkAction = new FileOpenWorkAction();
		fileSaveAction = new FileSaveAction();
		fileSaveAsAction = new FileSaveAsAction();
		fileExitAction = new FileExitAction();

		toolsAction = new ToolsAction();
		undoAction = new EditUndoAction();
		redoAction = new EditRedoAction();
		deleteElementAction = new EditDeleteElementAction();
		deleteFromTreeAction = new EditDeleteFromTreeAction();
		rotateCCWAction = new EditRotateCCWAction();
		rotateCWAction = new EditRotateCWAction();
		searchAction = new SearchAction();
		cutAction = new EditCutAction();
		copyAction = new EditCopyAction();
		pasteAction = new EditPasteAction();

		viewZoomInAction = new ViewZoomInAction();
		viewZoomOutAction = new ViewZoomOutAction();
		viewLassoZoomAction = new ViewLassoZoomAction();
		viewZoomBFAction = new ViewZoomBFAction();

		selectAllAction = new SelectAllAction();

		windowsCasAction = new WindowCasAction();
		windowVertAction = new WindowVertAction();
		windowHorAction = new WindowHorAction();
		windowPrevAction = new WindowPrevAction();
		windowNextAction = new WindowNextAction();

		helpAboutAction = new HelpAboutAction();

		toolsCircleAction = new ToolsCircleAction();
		toolsRectangleAction = new ToolsRectangleAction();
		toolsSelectAction = new ToolsSelectAction();
		toolsTriangleAction = new ToolsTriangleAction();
		toolsStarAction = new ToolsStarAction();

	}

	public FileNewDiagAction getFileNewDiagAction() {
		return fileNewDiagAction;
	}

	public FileNewProjAction getFileNewProjAction() {
		return fileNewProjAction;
	}

	public FileOpenProjAction getFileOpenProjAction() {
		return fileOpenProjAction;
	}

	public FileOpenWorkAction getFileOpenWorkAction() {
		return fileOpenWorkAction;
	}

	public FileSaveAction getFileSaveAction() {
		return fileSaveAction;
	}

	public FileSaveAsAction getFileSaveAsAction() {
		return fileSaveAsAction;
	}

	public FileExitAction getFileExitAction() {
		return fileExitAction;
	}

	public ToolsAction getToolsAction() {
		return toolsAction;
	}

	public EditDeleteElementAction getDeleteElementAction() {
		return deleteElementAction;
	}

	public EditDeleteFromTreeAction getDeleteFromTreeAction() {
		return deleteFromTreeAction;
	}

	public EditRotateCCWAction getRotateCCWAction() {
		return rotateCCWAction;
	}

	public EditRotateCWAction getRotateCWAction() {
		return rotateCWAction;
	}

	public SearchAction getSearchAction() {
		return searchAction;
	}

	public ViewZoomInAction getViewZoomInAction() {
		return viewZoomInAction;
	}

	public ViewZoomOutAction getViewZoomOutAction() {
		return viewZoomOutAction;
	}

	public ViewLassoZoomAction getViewLassoZoomAction() {
		return viewLassoZoomAction;
	}

	public ViewZoomBFAction getViewZoomBFAction() {
		return viewZoomBFAction;
	}

	public SelectAllAction getSelectAllAction() {
		return selectAllAction;
	}

	public WindowCasAction getWindowsCasAction() {
		return windowsCasAction;
	}

	public WindowVertAction getWindowVertAction() {
		return windowVertAction;
	}

	public WindowHorAction getWindowHorAction() {
		return windowHorAction;
	}

	public WindowPrevAction getWindowPrevAction() {
		return windowPrevAction;
	}

	public WindowNextAction getWindowNextAction() {
		return windowNextAction;
	}

	public HelpAboutAction getHelpAboutAction() {
		return helpAboutAction;
	}

	public ToolsCircleAction getToolsCircleAction() {
		return toolsCircleAction;
	}

	public ToolsRectangleAction getToolsRectangleAction() {
		return toolsRectangleAction;
	}

	public ToolsSelectAction getToolsSelectAction() {
		return toolsSelectAction;
	}

	public ToolsTriangleAction getToolsTriangleAction() {
		return toolsTriangleAction;
	}

	public ToolsStarAction getToolsStarAction() {
		return toolsStarAction;
	}

	public void setToolsCircleAction(ToolsCircleAction toolsCircleAction) {
		this.toolsCircleAction = toolsCircleAction;
	}

	public void setToolsRectangleAction(
			ToolsRectangleAction toolsRectangleAction) {
		this.toolsRectangleAction = toolsRectangleAction;
	}

	public void setToolsSelectAction(ToolsSelectAction toolsSelectAction) {
		this.toolsSelectAction = toolsSelectAction;
	}

	public void setToolsTriangleAction(ToolsTriangleAction toolsTriangleAction) {
		this.toolsTriangleAction = toolsTriangleAction;
	}

	public void setToolsStarAction(ToolsStarAction toolsStarAction) {
		this.toolsStarAction = toolsStarAction;
	}

	public void setFileNewDiagAction(FileNewDiagAction fileNewDiagAction) {
		this.fileNewDiagAction = fileNewDiagAction;
	}

	public void setFileNewProjAction(FileNewProjAction fileNewProjAction) {
		this.fileNewProjAction = fileNewProjAction;
	}

	public void setFileOpenProjAction(FileOpenProjAction fileOpenProjAction) {
		this.fileOpenProjAction = fileOpenProjAction;
	}

	public void setFileSaveAction(FileSaveAction fileSaveAction) {
		this.fileSaveAction = fileSaveAction;
	}

	public void setFileSaveAsAction(FileSaveAsAction fileSaveAsAction) {
		this.fileSaveAsAction = fileSaveAsAction;
	}

	public void setFileExitAction(FileExitAction fileExitAction) {
		this.fileExitAction = fileExitAction;
	}

	public void setToolsAction(ToolsAction toolsAction) {
		this.toolsAction = toolsAction;
	}

	public void setDeleteElementAction(EditDeleteElementAction deleteElementAction) {
		this.deleteElementAction = deleteElementAction;
	}

	public void setDeleteFromTreeAction(
			EditDeleteFromTreeAction deleteFromTreeAction) {
		this.deleteFromTreeAction = deleteFromTreeAction;
	}

	public void setRotateCCWAction(EditRotateCCWAction rotateCCWAction) {
		this.rotateCCWAction = rotateCCWAction;
	}

	public void setRotateCWAction(EditRotateCWAction rotateCWAction) {
		this.rotateCWAction = rotateCWAction;
	}

	public EditCutAction getCutAction() {
		return cutAction;
	}

	public void setCutAction(EditCutAction cutAction) {
		this.cutAction = cutAction;
	}

	public EditCopyAction getCopyAction() {
		return copyAction;
	}

	public void setCopyAction(EditCopyAction copyAction) {
		this.copyAction = copyAction;
	}

	public EditPasteAction getPasteAction() {
		return pasteAction;
	}

	public void setPasteAction(EditPasteAction pasteAction) {
		this.pasteAction = pasteAction;
	}

	public EditUndoAction getUndoAction() {
		return undoAction;
	}

	public void setUndoAction(EditUndoAction undoAction) {
		this.undoAction = undoAction;
	}

	public EditRedoAction getRedoAction() {
		return redoAction;
	}

	public void setRedoAction(EditRedoAction redoAction) {
		this.redoAction = redoAction;
	}

	public void setSearchAction(SearchAction searchAction) {
		this.searchAction = searchAction;
	}

	public void setViewZoomInAction(ViewZoomInAction viewZoomInAction) {
		this.viewZoomInAction = viewZoomInAction;
	}

	public void setViewZoomOutAction(ViewZoomOutAction viewZoomOutAction) {
		this.viewZoomOutAction = viewZoomOutAction;
	}

	public void setViewLassoZoomAction(ViewLassoZoomAction viewLassoZoomAction) {
		this.viewLassoZoomAction = viewLassoZoomAction;
	}

	public void setViewZoomBFAction(ViewZoomBFAction viewZoomBFAction) {
		this.viewZoomBFAction = viewZoomBFAction;
	}

	public void setSelectAllAction(SelectAllAction selectAllAction) {
		this.selectAllAction = selectAllAction;
	}

	public void setWindowsCasAction(WindowCasAction windowsCasAction) {
		this.windowsCasAction = windowsCasAction;
	}

	public void setWindowVertAction(WindowVertAction windowVertAction) {
		this.windowVertAction = windowVertAction;
	}

	public void setWindowHorAction(WindowHorAction windowHorAction) {
		this.windowHorAction = windowHorAction;
	}

	public void setWindowPrevAction(WindowPrevAction windowPrevAction) {
		this.windowPrevAction = windowPrevAction;
	}

	public void setWindowNextAction(WindowNextAction windowNextAction) {
		this.windowNextAction = windowNextAction;
	}

	public void setHelpAboutAction(HelpAboutAction helpAboutAction) {
		this.helpAboutAction = helpAboutAction;
	}

}
