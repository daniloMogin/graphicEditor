package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.KeyStroke;

import models.elements.DiagramElement;
import workspace.view.DiagramView;
import app.MainFrame;

public class EditDeleteElementAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2155351510730328872L;

	public EditDeleteElementAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke((char) KeyEvent.VK_DELETE));
		putValue(SMALL_ICON, loadIcon("../images/15/delete.png"));
		putValue(NAME, "Delete Element");
		putValue(SHORT_DESCRIPTION, "Delete Element");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		deleteSelection();

	}

	private void deleteSelection() {
		DiagramView view = (DiagramView) MainFrame.getInstance().getDesktop()
				.getSelectedFrame();
		view.startSelectState();
		if (!view.getDiagram().getSelectionModel().getSelectionList().isEmpty()) {
			Iterator<DiagramElement> it = view.getDiagram().getSelectionModel()
					.getSelectionListIterator();
			while (it.hasNext()) {
				DiagramElement element = it.next();
				view.getDiagram().getModel().removeElement(element);

			}
			view.getDiagram().getSelectionModel().removeAllFromSelectionList();

		}
	}

}
