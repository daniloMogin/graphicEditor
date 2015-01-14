package windowListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MyWindowListener extends WindowAdapter {

	/**
	 * Metoda za zatvaranje aplikacije
	 * 
	 * @param windowListener
	 */

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		JFrame frame = (JFrame) e.getComponent();
		int code = JOptionPane.showConfirmDialog(frame,
				"Are you sure you want to exit Graphic Editor?",
				"Exit Graphic Editor?", JOptionPane.YES_NO_OPTION);
		if (code != JOptionPane.YES_OPTION) {

			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else {
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
	}

}
