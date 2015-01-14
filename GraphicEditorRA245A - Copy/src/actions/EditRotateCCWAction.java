package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.KeyStroke;

import models.elements.DiagramDevice;
import models.elements.DiagramElement;
import workspace.view.DiagramView;
import app.MainFrame;

public class EditRotateCCWAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8844232992775782542L;

	public EditRotateCCWAction() {

		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
				KeyEvent.VK_OPEN_BRACKET, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/rotateCCW.png"));
		putValue(NAME, "Rotate Counterclockwise");
		putValue(SHORT_DESCRIPTION, "Rotate Counterclockwise");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Iterator<DiagramElement> it = ((DiagramView) (MainFrame.getInstance()
				.getDesktop().getSelectedFrame())).getDiagram()
				.getSelectionModel().getSelectionListIterator();
		while (it.hasNext()) {

			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				device.setRotation(device.getRotation() - Math.PI / 2);
			}
		}
		((DiagramView) (MainFrame.getInstance().getDesktop().getSelectedFrame()))
				.updatePerformed(null);

	}

}
