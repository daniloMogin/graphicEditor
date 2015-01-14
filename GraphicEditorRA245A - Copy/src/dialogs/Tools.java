package dialogs;

import java.awt.Frame;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tools extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3754125889081627665L;

	public Tools(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(120, 200);
		setLocation(1070, 220);
//		setLocationRelativeTo(parent);
		setResizable(false);
		setAlwaysOnTop(true);
		setLayout(new GridLayout(3, 2, 15, 15));
		JPanel panel = (JPanel) this.getContentPane();
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));

		/* ++++++++++++++ TOOLS ++++++++++++++++ */
		URL selectURL = getClass().getResource("../images/25/selectJ.png");
		ImageIcon iconSelect = new ImageIcon(selectURL);
		URL rectangleURL = getClass().getResource("../images/25/rectangle.png");
		ImageIcon iconRectangle = new ImageIcon(rectangleURL);
		URL circleURL = getClass().getResource("../images/25/circle.png");
		ImageIcon iconCircle = new ImageIcon(circleURL);

		JButton selectComWin = new JButton(iconSelect);
		selectComWin.setToolTipText("Select");
		JButton rectangleComWin = new JButton(iconRectangle);
		rectangleComWin.setToolTipText("Draw Rectangle");
		JButton circleComWin = new JButton(iconCircle);
		circleComWin.setToolTipText("Draw Circle");


		add(selectComWin);
		add(rectangleComWin);
		add(circleComWin);

	}

}
