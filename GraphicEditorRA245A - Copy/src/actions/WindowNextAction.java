package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;

import app.MainFrame;

public class WindowNextAction extends AbstractGEDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -332796244934354280L;

	private int current;
	private int next;

	public WindowNextAction() {

		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("../images/15/WNext.png"));
		putValue(NAME, "Next Window");
		putValue(SHORT_DESCRIPTION, "Next Window");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		nextWindow();

	}

	public void nextWindow() {
		current = MainFrame
				.getInstance()
				.getDiagramView()
				.indexOf(
						MainFrame.getInstance().getDesktop().getSelectedFrame());

		if (MainFrame.getInstance().getDesktop().getSelectedFrame() == null)
			return;

		if (current == MainFrame.getInstance().getDiagramView().size() - 1)
			next = 0;
		else
			next = current + 1;

		while (!MainFrame.getInstance().getDiagramView().get(next).isVisible()
				&& next <= MainFrame.getInstance().getDiagramView().size() - 1)
			next++;

		if (next == MainFrame.getInstance().getDiagramView().size()) {
			next = 0;

			while (!MainFrame.getInstance().getDiagramView().get(next)
					.isVisible())
				next++;
		}

		try {
			MainFrame.getInstance().getDiagramView().get(next)
					.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
