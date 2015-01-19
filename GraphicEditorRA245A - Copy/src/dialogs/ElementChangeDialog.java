package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import models.elements.DiagramElement;
import app.MainFrame;

public class ElementChangeDialog extends JDialog {

	private static final long serialVersionUID = 6853271078013771392L;

	public ElementChangeDialog(final Frame owner, JDialog window,
			final DiagramElement element) {
		super();

		setSize(250, 300);
		setResizable(false);
		setLocationRelativeTo(owner);
		setLayout(new BorderLayout());
		setTitle(element.getClass().getName().replace("models.elements.", "")
				.replace("Element", "")
				+ " - " + element.getName());

		// kreiram PANEL u koju cu da stavljam sve componente
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.LIGHT_GRAY);

		// kreiram PANEL za Name
		JPanel panelName = new JPanel();
		panelName.setPreferredSize(new Dimension(250, 42));
		panelName.setBackground(Color.LIGHT_GRAY);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setPreferredSize(new Dimension(230, 15));

		final JTextField nameTextField = new JTextField();
		nameTextField.setText(element.getName());
		nameTextField.setBorder(new LineBorder(Color.BLACK, 2));
		nameTextField.setPreferredSize(new Dimension(230, 25));

		panelName.add(nameLabel);
		panelName.add(nameTextField);
		mainPanel.add(panelName);

		// kreiram PANEL za Description
		JPanel panelDesc = new JPanel();
		panelDesc.setPreferredSize(new Dimension(250, 67));
		panelDesc.setBackground(Color.LIGHT_GRAY);

		JLabel descLabel = new JLabel("Description");
		descLabel.setPreferredSize(new Dimension(230, 15));

		final JTextArea descTextArea = new JTextArea();
		descTextArea.setText(element.getDescription());
		descTextArea.setBorder(new LineBorder(Color.BLACK, 2));
		descTextArea.setPreferredSize(new Dimension(230, 50));

		panelDesc.add(descLabel);
		panelDesc.add(descTextArea);
		mainPanel.add(panelDesc);

		// kreiram PANEL za Color
		JPanel panelColor = new JPanel();
		panelColor.setPreferredSize(new Dimension(250, 37));
		panelColor.setLayout(new GridLayout(2, 2));
		panelColor.setBackground(Color.LIGHT_GRAY);

		JLabel colorNameLabel = new JLabel("Current Color");

		JLabel emptyLabel = new JLabel("");

		final JPanel paintPanel = new JPanel();
		paintPanel.setBackground((Color) element.getPaint());
		paintPanel.setBorder(new LineBorder(Color.BLACK, 2));

		@SuppressWarnings("unused")
		JColorChooser colorChooser = new JColorChooser();
		JButton changeButton = new JButton("Change Color");
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paintPanel.setBackground(JColorChooser.showDialog(
						MainFrame.getInstance(), "Choose Background Color",
						Color.WHITE));
			}
		});

		panelColor.add(colorNameLabel);
		panelColor.add(emptyLabel);
		panelColor.add(paintPanel);
		panelColor.add(changeButton);

		mainPanel.add(panelColor);

		// kreiram PANEL za buttons
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.LIGHT_GRAY);
		panelButtons.setPreferredSize(new Dimension(250, 17));

		// ok apply cancel
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// okAction();
				element.setName(nameTextField.getText());
				element.setPaint(paintPanel.getBackground());
				element.setDescription(descTextArea.getText());

				setTitle(element.getClass().getName()
						.replace("models.elements.", "").replace("Element", "")
						+ " - " + element.getName());

				owner.repaint();
				dispose();
			}
		});
		panelButtons.add(okButton);

		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				element.setName(nameTextField.getText());
				element.setPaint(paintPanel.getBackground());
				element.setDescription(descTextArea.getText());

				setTitle(element.getClass().getName()
						.replace("models.elements.", "").replace("Element", "")
						+ " - " + element.getName());

				owner.repaint();
			}
		});
		panelButtons.add(applyButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panelButtons.add(cancelButton);

		mainPanel.add(panelButtons);

		this.setBackground(Color.RED);

		this.add(mainPanel);
	} // kraj konstruktora

}
