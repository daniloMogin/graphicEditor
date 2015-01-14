package dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class About extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3754125889081627665L;

	public About(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(600, 500);
		setLocationRelativeTo(parent);
		setResizable(false);
		setAlwaysOnTop(true);
		setLayout(new GridLayout(2, 1));

		/* +++++++++++++++ GORE ++++++++++++++++ */
		JPanel txa1 = new JPanel();
		txa1.setBorder(BorderFactory.createLoweredBevelBorder());
		txa1.setLayout(new BorderLayout());

		JPanel txa12 = new JPanel();
		txa12.setLayout(new BoxLayout(txa12, BoxLayout.Y_AXIS));
		txa12.setBorder(new EmptyBorder(15, 15, 15, 15));
		JLabel lbl1 = new JLabel("Graphic Editor");
		JLabel lbl2 = new JLabel("Verzion: UltraMegaGigaTurboFolk 11.014");
		JLabel lbl3 = new JLabel("Danilo Mogin RA245/2013");
		JLabel lbl4 = new JLabel("danilo.mogin@gmail.com");
		txa12.add(lbl1);
		txa12.add(lbl2);
		txa12.add(lbl3);
		txa12.add(lbl4);

		JPanel txa13 = new JPanel();
		JLabel lblSlika = new JLabel();
		URL imageURL = getClass().getResource("../images/slikaJa.jpg");
		ImageIcon imgJa = new ImageIcon(imageURL);
		lblSlika.setIcon(imgJa);
		txa13.add(lblSlika);

		txa1.add(txa12, BorderLayout.WEST);
		txa1.add(txa13, BorderLayout.CENTER);
		/* +++++++++++++++++++++++++++++++++++++ */

		/* ++++++++++++++ DOLE +++++++++++++++++ */
		JPanel txa2 = new JPanel();
		txa2.setLayout(new BorderLayout());
		txa2.setBorder(new EmptyBorder(15, 15, 15, 15));
		JLabel lbl21 = new JLabel(
				"Copyright GraphicEditor contributors and others 2014, 2015.  All rights reserved.");
		txa2.add(lbl21, BorderLayout.SOUTH);

		JLabel lblLogo = new JLabel();
		URL imageURL1 = getClass().getResource("../images/logo2.png");
		ImageIcon img = new ImageIcon(imageURL1);
		lblLogo.setIcon(img);

		txa2.add(lblLogo);
		/* +++++++++++++++++++++++++++++++++++++ */

		add(txa1);
		add(txa2);

	}

}
