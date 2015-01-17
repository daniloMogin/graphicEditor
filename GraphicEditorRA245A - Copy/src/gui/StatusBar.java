package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1696597433259221588L;

	private StatusPane status = new StatusPane("  Welcome!");
	private StatusPane elementType = new StatusPane("GraphicEditor is ready!");
	private StatusPane elementName = new StatusPane("Element name");
	private StatusPane position = new StatusPane("Position");
	private StatusPane dimension = new StatusPane("Dimension");

	public StatusBar() {
		setLayout(new GridLayout(1, 5));
		add(status);
		add(elementType);
		add(elementName);
		add(position);
		add(dimension);
	}

	private class StatusPane extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 420903054830822287L;

		public StatusPane(String text) {
			setBorder(BorderFactory
					.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
			setBackground(Color.GRAY);
			setPreferredSize(new Dimension(170, 20));
			setHorizontalAlignment(CENTER);
			setText(text);
		}

	
	}

	public void setStatus(String status) {
		this.status.setText(status);
	}

	public void setElementType(String elementType) {
		this.elementType.setText(elementType);
	}

	public void setElementName(String elementName) {
		this.elementName.setText(elementName);
	}

	public void setPosition(String position) {
		this.position.setText(position);
	}

	public void setDimension(String dimension) {
		this.dimension.setText(dimension);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
