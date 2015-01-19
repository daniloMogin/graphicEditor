package states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import models.elements.DiagramDevice;
import models.elements.DiagramElement;
import workspace.view.DiagramView;
import workspace.view.DiagramView.Handle;

import commands.ResizeCommand;

public class ResizeState extends State {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6965524213481718446L;

	double delta4X = 0;
	double delta4Y = 0;
	double delta5X = 0;
	double delta5Y = 0;
	double delta6X = 0;
	double delta6Y = 0;
	double delta7X = 0;
	double delta7Y = 0;

	private boolean justStarted = true;
	private ArrayList<Double> listOfScales = new ArrayList<Double>();
	private ArrayList<Point2D> listOfPositions = new ArrayList<Point2D>();
	private int iterator = 0;

	DiagramDevice deviceForDifference;

	Handle handleInMotion = null;
	private DiagramView med;

	public ResizeState(DiagramView md) {
		med = md;
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

		Point2D position;

		if (!justStarted) {
			position = e.getPoint();
			med.transformToUserSpace(position);
		} else {
			position = med.getLastPosition();
		}

		setupDifference(position);

		if (handleInMotion == null) {
			handleInMotion = med.getDeviceAndHandleForPoint(position);
		}
		if (handleInMotion != null) {

			Iterator<DiagramElement> it = med.getDiagram().getSelectionModel()
					.getSelectionListIterator();
			while (it.hasNext()) {

				DiagramElement element = it.next();
				if (element instanceof DiagramDevice) {
					DiagramDevice device = (DiagramDevice) element;

					if (iterator <= med.getDiagram().getSelectionModel()
							.getSelectionList().size() - 1) {
						listOfScales.add(device.getScale());
						listOfPositions.add(device.getPosition());
						iterator++;
					}

					switch (handleInMotion.ordinal()) {
					// southeast
					case 4: {
						double deltaX = delta4X;
						double deltaY = delta4Y;
						double newWidth = device.getSize().getWidth() + deltaX;
						double newHeight = device.getSize().getHeight()
								+ deltaY;
						double scaleX = newWidth
								/ device.getInitSize().getWidth();
						double scaleY = newHeight
								/ device.getInitSize().getHeight();
						double newScale = 1;
						if (scaleX < scaleY)
							newScale = scaleX;
						else
							newScale = scaleY;
						if (newScale < 0.2)
							device.setScale(0.2);
						else if (newScale > 5)
							device.setScale(5);
						else
							device.setScale(newScale);
						break;
					}
					// southwest
					case 5: {
						double deltaX = delta5X;
						double deltaY = delta5Y;
						double oldWidth = device.getSize().getWidth();
						double newWidth = device.getSize().getWidth() + deltaX;
						double newHeight = device.getSize().getHeight()
								+ deltaY;
						double scaleX = newWidth
								/ device.getInitSize().getWidth();
						double scaleY = newHeight
								/ device.getInitSize().getHeight();
						double newScale = 1;
						if (scaleX < scaleY)
							newScale = scaleX;
						else
							newScale = scaleY;
						if (newScale < 0.2)
							device.setScale(0.2);
						else if (newScale > 5)
							device.setScale(5);
						else
							device.setScale(newScale);
						double newX = device.getPosition().getX() + oldWidth
								- device.getSize().getWidth();
						device.setPosition(new Point2D.Double(newX, device
								.getPosition().getY()));
						break;
					}
					// northeast
					case 6: {
						double deltaX = delta6X;
						double deltaY = delta6Y;
						double oldHeight = device.getSize().getHeight();
						double newWidth = device.getSize().getWidth() + deltaX;
						double newHeight = device.getSize().getHeight()
								+ deltaY;
						double scaleX = newWidth
								/ device.getInitSize().getWidth();
						double scaleY = newHeight
								/ device.getInitSize().getHeight();
						double newScale = 1;
						if (scaleX < scaleY)
							newScale = scaleX;
						else
							newScale = scaleY;
						if (newScale < 0.2)
							device.setScale(0.2);
						else if (newScale > 5)
							device.setScale(5);
						else
							device.setScale(newScale);
						double newY = device.getPosition().getY() + oldHeight
								- device.getSize().getHeight();
						device.setPosition(new Point2D.Double(device
								.getPosition().getX(), newY));
						break;
					}
					// northwest
					case 7: {
						double deltaX = delta7X;
						double deltaY = delta7Y;
						double oldHeight = device.getSize().getHeight();
						double oldWidth = device.getSize().getWidth();
						double newWidth = device.getSize().getWidth() + deltaX;
						double newHeight = device.getSize().getHeight()
								+ deltaY;
						double scaleX = newWidth
								/ device.getInitSize().getWidth();
						double scaleY = newHeight
								/ device.getInitSize().getHeight();
						double newScale = 1;
						if (scaleX < scaleY)
							newScale = scaleX;
						else
							newScale = scaleY;
						if (newScale < 0.2)
							device.setScale(0.2);
						else if (newScale > 5)
							device.setScale(5);
						else
							device.setScale(newScale);
						double newY = device.getPosition().getY() + oldHeight
								- device.getSize().getHeight();
						double newX = device.getPosition().getX() + oldWidth
								- device.getSize().getWidth();
						device.setPosition(new Point2D.Double(newX, newY));
						break;
					}
					}
				}
				med.updatePerformed(null);
				justStarted = false;
			}

		}
	}

	// namestanje koordinata posle resize
	private void setupDifference(Point2D position) {
		if (deviceForDifference == null)
			deviceForDifference = med.getHandlesElement(position);

		if (deviceForDifference == null)
			return;
		if (deviceForDifference.getRotation() == 0
				|| deviceForDifference.getRotation() == Math.PI
				|| deviceForDifference.getRotation() == -Math.PI) {
			delta4X = position.getX()
					- (deviceForDifference.getPosition().getX() + deviceForDifference
							.getSize().getWidth());
			delta4Y = position.getY()
					- (deviceForDifference.getPosition().getY() + deviceForDifference
							.getSize().getHeight());

			delta5X = (deviceForDifference.getPosition().getX() - position
					.getX());
			delta5Y = position.getY()
					- (deviceForDifference.getPosition().getY() + deviceForDifference
							.getSize().getHeight());

			delta6X = position.getX()
					- (deviceForDifference.getPosition().getX() + deviceForDifference
							.getSize().getWidth());
			delta6Y = deviceForDifference.getPosition().getY()
					- position.getY();

			delta7X = (deviceForDifference.getPosition().getX() - position
					.getX());
			delta7Y = deviceForDifference.getPosition().getY()
					- position.getY();
		} else {
			double delta = (deviceForDifference.getSize().getWidth() - deviceForDifference
					.getSize().getHeight()) / 2;

			delta4X = position.getX()
					- (deviceForDifference.getPosition().getX()
							+ deviceForDifference.getSize().getWidth() - delta);
			delta4Y = position.getY()
					- (deviceForDifference.getPosition().getY()
							+ deviceForDifference.getSize().getHeight() + delta);

			delta5X = (deviceForDifference.getPosition().getX() - (position
					.getX() - delta));
			delta5Y = position.getY()
					- (deviceForDifference.getPosition().getY()
							+ deviceForDifference.getSize().getHeight() + delta);

			delta6X = position.getX()
					- (deviceForDifference.getPosition().getX()
							+ deviceForDifference.getSize().getWidth() - delta);
			delta6Y = deviceForDifference.getPosition().getY()
					- (position.getY() + delta);

			delta7X = (deviceForDifference.getPosition().getX() - (position
					.getX() - delta));
			delta7Y = deviceForDifference.getPosition().getY()
					- (position.getY() + delta);
		}
	}

	public void mouseReleased(MouseEvent e) {
		handleInMotion = null;
		med.startSelectState();
		ArrayList<DiagramElement> selectionList = med.getDiagram()
				.getSelectionModel().getSelectionList();

		iterator = 0;
		med.getCommandManager().addCommand(
				new ResizeCommand(med, selectionList, listOfScales,
						listOfPositions));
		med.startSelectState();
		deviceForDifference = null;
	}

	public boolean isJustStarted() {
		return justStarted;
	}

	public void setJustStarted(boolean justStarted) {
		this.justStarted = justStarted;
	}

}
