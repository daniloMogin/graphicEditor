package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;

import app.MainFrame;

public class WindowPrevAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7305172825723228257L;

	private int current;
	private int prev;

	public WindowPrevAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/WPrev.png"));
		putValue(NAME, "Previous Window");
		putValue(SHORT_DESCRIPTION, "Previous Window");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		prevWindow();

	}

	public void prevWindow() {
		current = MainFrame
				.getInstance()
				.getDiagramView()
				.indexOf(
						MainFrame.getInstance().getDesktop().getSelectedFrame());

		if (MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;

		if (current == 0)
			prev = MainFrame.getInstance().getDiagramView().size() - 1;
		else
			prev = current - 1;

		while (!MainFrame.getInstance().getDiagramView().get(prev).isVisible()
				&& prev >= 0)
			prev--;

		if (prev == -1) {
			prev = MainFrame.getInstance().getDiagramView().size() - 1;

			while (!MainFrame.getInstance().getDiagramView().get(prev)
					.isVisible())
				prev--;
		}

		try {
			MainFrame.getInstance().getDiagramView().get(prev)
					.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
