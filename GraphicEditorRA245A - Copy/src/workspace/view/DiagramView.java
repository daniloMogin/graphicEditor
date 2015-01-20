package workspace.view;

import java.awt.Adjustable;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.datatransfer.Transferable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.tree.TreePath;

import models.DiagramElementsSelection;
import models.elements.DiagramDevice;
import models.elements.DiagramElement;
import states.StateManager;
import workspace.Diagram;
import workspace.Workspace;
import workspace.view.painters.ElementPainter;
import app.MainFrame;

import commands.CommandManager;

import events.UpdateEvent;
import events.UpdateListener;

public class DiagramView extends JInternalFrame implements
		InternalFrameListener, UpdateListener, AdjustmentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3705832808499127582L;

	static int openFrameCount = 0;
	static int offsetCount = 0;
	static int off = 0;

	// služe nam za određivanje pozicije unutrašnjeg prozora
	static final int xOffset = 20, yOffset = 30;

	private Diagram diagram;

	private JPanel framework;
	private JScrollBar sbVertical;
	private JScrollBar sbHorizontal;

	// inicijalna pozicija skrol bara, vazna kod pomeranja skrol bara
	private int hScrollValue = 140;
	private int vScrollValue = 140;

	double translateX = 0;
	double translateY = 0;
	double scaling = 1;
	final static double translateFactor = 10;
	final public static double scalingFactor = 1.2;

	// tacka koja nam za sada sluzi za lasso
	private Point2D lastPosition = null;
	private Rectangle2D selectionRectangle = new Rectangle2D.Double();

	private CommandManager commandManager = new CommandManager();

	private AffineTransform transformation = new AffineTransform();

	public static final int CIRCLE = 1;
	public static final int RECTANGLE = 2;
	public static final int STAR = 3;
	public static final int TRIANGLE = 4;

	private StateManager stateManager = new StateManager(this);

	// podrska za rad sa StateManagerom
	// ----------------------------------------------
	public void startSelectState() {
		stateManager.setSelectState();
		MainFrame.getInstance().getStatusBar().setStatus("Select State");
	}

	public void startCircleState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setCircleState();
		MainFrame.getInstance().getStatusBar().setStatus("Add Circle Element");
	}

	public void startRectangleState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setRectangleState();
		MainFrame.getInstance().getStatusBar()
				.setStatus("Add Rectangle Element");
	}

	public void startTriangleState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setTriangleState();
		MainFrame.getInstance().getStatusBar()
				.setStatus("Add Triangle Element");
	}

	public void startStarState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setStarState();
		MainFrame.getInstance().getStatusBar().setStatus("Add Star Element");
	}

	public void startResizeState() {
		stateManager.setResizeState();
		MainFrame.getInstance().getStatusBar().setStatus("Resize State");
	}

	public void startMoveState() {
		stateManager.setMoveState();
		MainFrame.getInstance().getStatusBar().setStatus("Move State");
	}

	public void startLassoState() {
		stateManager.setLassoState();
		MainFrame.getInstance().getStatusBar().setStatus("Lasso State");
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	// -----------------------------------------------
	/**
	 * Handlovi za selekciju su identifikovani stranama sveta.
	 */
	public enum Handle {
		North, South, East, West, SouthEast, SouthWest, NorthEast, NorthWest
	}

	public enum Direction {
		Up, Down, Left, Right
	}

	static final int handleSize = 7;

	public DiagramView() {

		super("", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable
		if (yOffset * offsetCount > MainFrame.getInstance().getDesktop()
				.getSize().getHeight() - 150) {
			offsetCount = 0;
			off++;
		}
		offsetCount++;
		setLocation(xOffset * offsetCount + 60 * off, yOffset * offsetCount);
		setIconifiable(true);
		setClosable(true);

		setSize(400, 400);
		setVisible(true);
		addInternalFrameListener(this);

		framework = new Framework();
		framework.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		getContentPane().add(framework, BorderLayout.CENTER);
		framework.setBackground(new Color(127, 121, 96));

		// postavljanje horizontalnog i vertikalnog skrol bara
		sbHorizontal = new JScrollBar(JScrollBar.HORIZONTAL, hScrollValue, 20,
				0, 300);
		sbVertical = new JScrollBar(JScrollBar.VERTICAL, vScrollValue, 20, 0,
				300);

		sbHorizontal.addAdjustmentListener(this);
		sbVertical.addAdjustmentListener(this);

		this.add(sbHorizontal, BorderLayout.SOUTH);
		this.add(sbVertical, BorderLayout.EAST);
		// --------------------------------------------------------------------------

		DiagramController diagControl = new DiagramController();
		framework.addMouseListener(diagControl);
		framework.addMouseMotionListener(diagControl);
		framework.addMouseWheelListener(diagControl);

	}

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
		this.setName(diagram.getName());
		this.diagram.getModel().addUpdateListener(this);
		this.diagram.getSelectionModel().addUpdateListener(this);
		setTitle(diagram.getName());
	}

	public Diagram getDiagram() {
		return diagram;
	}

	private class DiagramController extends MouseAdapter implements
			MouseMotionListener {

		public void mousePressed(MouseEvent e) {
			lastPosition = e.getPoint();
			transformToUserSpace(lastPosition);
			getStateManager().getCurrentState().mousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			getStateManager().getCurrentState().mouseReleased(e);
		}

		public void mouseDragged(MouseEvent e) {
			getStateManager().getCurrentState().mouseDragged(e);
		}

		public void mouseMoved(MouseEvent e) {
			getStateManager().getCurrentState().mouseMoved(e);
		}

		public void mouseWheelMoved(MouseWheelEvent e) {

			if ((e.getModifiers() & MouseWheelEvent.CTRL_MASK) != 0) { // Ako
																		// pritisnut
																		// Ctrl
				// Radimo zoom u tački (diskretni zoom)
				// Prvo je potrebno da odredimo novo skaliranje
				double newScaling = scaling;
				if (e.getWheelRotation() > 0)

					newScaling *= (double) e.getWheelRotation() * scalingFactor;

				else {

					if (e.getWheelRotation() != 0) {

						newScaling /= -(double) e.getWheelRotation()
								* scalingFactor;

					}

				}
				// Zatim je potrebno da skaliranje održimo u intervalu [0.1, 10]
				if (newScaling < 0.1) {
					newScaling = 0.1;

				} else if (newScaling > 10) {
					newScaling = 10;
				}

				/*
				 * newScaling je novi parametar skaliranja (članovi m00 i m11
				 * transformacione matrice) Prilikom skaliranja dolazi do
				 * pomeranja userspace koordinata na kojima se nalazi pokazivač
				 * miša. Da bi dobili ispravan Point zoom moramo izvršiti
				 * translaciju tako da poništimo "smicanje" usled skaliranja tj.
				 * moramo postići da se userspace koordinate miša ne promene.
				 */

				Point2D oldPosition = e.getPoint();
				transformToUserSpace(oldPosition);

				scaling = newScaling;
				setupTransformation();

				Point2D newPosition = e.getPoint();
				transformToUserSpace(newPosition);

				translateX += newPosition.getX() - oldPosition.getX();
				translateY += newPosition.getY() - oldPosition.getY();

				// setupTransformation();

			} else if ((e.getModifiers() & MouseWheelEvent.SHIFT_MASK) != 0) { // Ako
																				// je
																				// pritisnut
																				// Shift

				translateX += (double) e.getWheelRotation() * translateFactor
						/ scaling;

			} else { // u ostalim slučajevima vršimo skrolovanje po Y osi

				translateY += (double) e.getWheelRotation() * translateFactor
						/ scaling;

			}

			setupTransformation();
			repaint();
		}

	}

	public void transformToUserSpace(Point2D deviceSpace) {
		try {
			transformation.inverseTransform(deviceSpace, deviceSpace);
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		repaint();

	}

	/**
	 * Podešava parametre transformacione matrice
	 */
	private void setupTransformation() {

		transformation.setToIdentity();
		// Zumiranje

		transformation.scale(scaling, scaling);
		// Skrolovanje
		transformation.translate(translateX, translateY);

	}

	public DiagramDevice getHandlesElement(Point2D point) {
		DiagramElement element;
		Handle handle = null;

		Iterator<DiagramElement> it = diagram.getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			element = it.next();
			handle = getHandleForPoint(element, point);
			if (handle != null)
				return (DiagramDevice) element;
		}
		return null;
	}

	private class Framework extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4608281852422924248L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.8f));

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			g2.transform(transformation);

			Iterator<DiagramElement> it = diagram.getModel()
					.getElementsIterator();
			while (it.hasNext()) {
				DiagramElement d = (DiagramElement) it.next();
				ElementPainter paint = (ElementPainter) d.getPainter();
				paint.paint(g2, d);

			}

			paintSelectionHandles(g2);

			// iscrtavanje pravougaonika za lasso
			g2.setColor(Color.BLACK);
			g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE,
					BasicStroke.JOIN_BEVEL, 1f, new float[] { (float) 3,
							(float) 6 }, 0));
			g2.draw(selectionRectangle);

		}

	}

	/**
	 * Iscrtavanje selekcionih hendlova oko selektovanog elementa
	 */
	private void paintSelectionHandles(Graphics2D g2) {

		Iterator<DiagramElement> it = diagram.getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			DiagramElement element = it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				// Iscrtavanje pravougaonika sa isprekidanom linijom
				g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE,
						BasicStroke.JOIN_BEVEL, 1f, new float[] { 3f, 6f }, 0));
				g2.setPaint(Color.BLACK);

				if (device.getRotation() == 0
						|| device.getRotation() == Math.PI
						|| device.getRotation() == -Math.PI)
					g2.drawRect((int) device.getPosition().getX(), (int) device
							.getPosition().getY(), (int) device.getSize()
							.getWidth(), (int) device.getSize().getHeight());

				else {
					double delta = (device.getSize().getWidth() - device
							.getSize().getHeight()) / 2;
					g2.drawRect((int) (device.getPosition().getX() + delta),
							(int) (device.getPosition().getY() - delta),
							(int) (device.getSize().getHeight()),
							(int) (device.getSize().getWidth()));
				}

				// Iscrtavanje hendlova
				for (Handle e : Handle.values()) {
					paintSelectionHandle(
							g2,
							getHandlePoint(device.getPosition(),
									device.getSize(), e, device));
				}
			}
		}
	}

	private void paintSelectionHandle(Graphics2D g2, Point2D position) {
		double size = handleSize;
		g2.fill(new Rectangle2D.Double(position.getX() - size / 2, position
				.getY() - size / 2, size, size));
	}

	private Point2D getHandlePoint(Point2D topLeft, Dimension2D size,
			Handle handlePosition, DiagramDevice device) {
		double x = 0, y = 0;
		
		if (device.getRotation() == 0 || device.getRotation() == Math.PI
				|| device.getRotation() == -Math.PI) {
			// Određivanje y koordinate

			// Ako su gornji hendlovi
			if (handlePosition == Handle.NorthWest
					|| handlePosition == Handle.North
					|| handlePosition == Handle.NorthEast) {
				y = topLeft.getY();
			}
			// Ako su centralni po y osi
			if (handlePosition == Handle.East || handlePosition == Handle.West) {
				y = topLeft.getY() + size.getHeight() / 2;
			}
			// Ako su donji
			if (handlePosition == Handle.SouthWest
					|| handlePosition == Handle.South
					|| handlePosition == Handle.SouthEast) {
				y = topLeft.getY() + size.getHeight();
			}

			// Određivanje x koordinate

			// Ako su levi
			if (handlePosition == Handle.NorthWest
					|| handlePosition == Handle.West
					|| handlePosition == Handle.SouthWest) {
				x = topLeft.getX();
			}
			// ako su centralni po x osi
			if (handlePosition == Handle.North
					|| handlePosition == Handle.South) {
				x = topLeft.getX() + size.getWidth() / 2;
			}
			// ako su desni
			if (handlePosition == Handle.NorthEast
					|| handlePosition == Handle.East
					|| handlePosition == Handle.SouthEast) {
				x = topLeft.getX() + size.getWidth();
			}
		} else {
			double delta = (device.getSize().getWidth() - device.getSize()
					.getHeight()) / 2;

			if (handlePosition == Handle.NorthWest
					|| handlePosition == Handle.North
					|| handlePosition == Handle.NorthEast) {
				y = topLeft.getY() - delta;
			}

			if (handlePosition == Handle.East || handlePosition == Handle.West) {
				y = topLeft.getY() + size.getWidth() / 2 - delta;
			}

			// Ako su donji
			if (handlePosition == Handle.SouthWest
					|| handlePosition == Handle.South
					|| handlePosition == Handle.SouthEast) {
				y = topLeft.getY() + size.getWidth() - delta;
			}
			
			// Ako su levi
			if (handlePosition == Handle.NorthWest
					|| handlePosition == Handle.West
					|| handlePosition == Handle.SouthWest) {
				x = topLeft.getX() + delta;
			}

			if (handlePosition == Handle.North
					|| handlePosition == Handle.South) {
				x = topLeft.getX() + size.getHeight() / 2 + delta;
			}

			// ako su desni
			if (handlePosition == Handle.NorthEast
					|| handlePosition == Handle.East
					|| handlePosition == Handle.SouthEast) {
				x = topLeft.getX() + size.getHeight() + delta;
			}
		}
		return new Point2D.Double(x, y);

	}

	/**
	 * Na osnovu hendla iznad koga se nalazi postavlja kursor
	 */
	public void setMouseCursor(Point2D point) {

		Handle handle = getDeviceAndHandleForPoint(point);

		if (handle != null) {
			Cursor cursor = null;

			switch (handle) {
			case North:
				cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
				break;
			case South:
				cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
				break;
			case East:
				cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
				break;
			case West:
				cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
				break;
			case SouthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case NorthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case SouthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case NorthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				break;
			}
			framework.setCursor(cursor);
		} else
			framework.setCursor(Cursor.getDefaultCursor());
	}

	/**
	 * Određuje handl i uređaj koji se nalazi na zadatoj lokaciji
	 * 
	 * @param point
	 *            Ulazni parametar koji određuje lokaciju
	 * @return Hendl za zadatu poziciju. Ukoliko je null tada je device
	 *         nedefinisan.
	 */
	public Handle getDeviceAndHandleForPoint(Point2D point) {
		DiagramElement element;

		Iterator<DiagramElement> it = diagram.getSelectionModel()
				.getSelectionListIterator();
		while (it.hasNext()) {
			element = it.next();
			return getHandleForPoint(element, point);
		}
		return null;
	}

	/**
	 * Za zadatu tačku i uređaj vraća hendl.
	 * 
	 * @param device
	 * @param point
	 * @return Hendl ukoliko je "pogođen", u suprotnom vraća null
	 */
	private Handle getHandleForPoint(DiagramElement element, Point2D point) {
		for (Handle h : Handle.values()) {
			if (isPointInHandle(element, point, h)) {
				return h;
			}
		}
		return null;
	}

	/**
	 * Za zadati uređaj, tačku i hendl određuje da li je tačka unutar hendla
	 * 
	 * @param device
	 * @param point
	 * @param handle
	 * @return
	 */
	private boolean isPointInHandle(DiagramElement element, Point2D point,
			Handle handle) {
		if (element instanceof DiagramDevice) {
			DiagramDevice device = (DiagramDevice) element;
			Point2D handleCenter = getHandlePoint(device.getPosition(),
					device.getSize(), handle, device);
			return ((Math.abs(point.getX() - handleCenter.getX()) <= (double) handleSize / 2) && (Math
					.abs(point.getY() - handleCenter.getY()) <= (double) handleSize / 2));
		} else
			return false;
	}

	public Point2D getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point2D lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Rectangle2D getSelectionRectangle() {
		return selectionRectangle;
	}

	public void setSelectionRectangle(Rectangle2D selectionRectangle) {
		this.selectionRectangle = selectionRectangle;
	}

	/**
	 * Metoda radi best-fit zoom elemenata na dijagramu
	 * 
	 */
	public void bestFitZoom() {
		selectionFit();

	}

	/**
	 * Metoda odredjuje granice regiona u kojima se nalaze elementi dijagrama
	 */

	@SuppressWarnings("rawtypes")
	public void selectionFit() {
		double minX = 0, minY = 0, maxX = 0, maxY = 0;
		Iterator it = diagram.getModel().getElementsIterator();
		if (it.hasNext()) {
			DiagramElement element = (DiagramElement) it.next();
			if (element instanceof DiagramDevice) {
				DiagramDevice device = (DiagramDevice) element;
				minX = device.getPosition().getX();
				minY = device.getPosition().getY();
				maxX = device.getPosition().getX()
						+ device.getSize().getWidth();
				maxY = device.getPosition().getY()
						+ device.getSize().getHeight();
				while (it.hasNext()) {
					element = (DiagramElement) it.next();
					if (element instanceof DiagramDevice) {
						device = (DiagramDevice) element;
						if (device.getPosition().getX() < minX)
							minX = device.getPosition().getX();
						if (device.getPosition().getX()
								+ device.getSize().getWidth() > maxX)
							maxX = device.getPosition().getX()
									+ device.getSize().getWidth();
						if (device.getPosition().getY() < minY)
							minY = device.getPosition().getY();
						if (device.getPosition().getY()
								+ device.getSize().getHeight() > maxY)
							maxY = device.getPosition().getY()
									+ device.getSize().getHeight();
					}
				}
			}
		}
		regionZoom(minX, maxX, minY, maxY);
	}

	/**
	 * Metoda radi zoom nad oznacenim regionom
	 * 
	 * @param minX
	 *            - leva granica
	 * @param maxX
	 *            - desna granica
	 * @param minY
	 *            - gornja granica
	 * @param maxY
	 *            - donja granica
	 */
	public void regionZoom(double minX, double maxX, double minY, double maxY) {
		double minXX, maxXX, minYY, maxYY;
		maxXX = transformLineToUserSpaceX(maxX);
		minXX = transformLineToUserSpaceX(minX);
		maxYY = transformLineToUserSpaceY(maxY);
		minYY = transformLineToUserSpaceY(minY);

		double regionWidth = maxXX - minXX;
		double regionHeight = maxYY - minYY;
		int deviceWidth = this.getWidth();
		int deviceHeight = this.getHeight();
		double newScaling = transformation.getScaleX();
		if (regionWidth != 0 && regionHeight != 0) {
			double scaleX = (deviceWidth / regionWidth);
			double scaleY = (deviceHeight / regionHeight);

			if (scaleX < scaleY) {
				newScaling *= scaleX;
			} else {
				newScaling *= scaleY;
			}
			if (newScaling < 0.1)
				newScaling = 0.1;
			if (newScaling > 10)
				newScaling = 10;
			transformation.setToScale(newScaling, newScaling);
		}

		maxXX = transformLineToUserSpaceX(maxX);
		minXX = transformLineToUserSpaceX(minX);
		maxYY = transformLineToUserSpaceY(maxY);
		minYY = transformLineToUserSpaceY(minY);
		regionWidth = maxXX - minXX;
		regionHeight = maxYY - minYY;
		transformation.translate(
				(-minXX + (deviceWidth - regionWidth) / 2)
						/ transformation.getScaleX(),
				(-minYY + (deviceHeight - regionHeight) / 2)
						/ transformation.getScaleY());
		repaint();
	}

	public double transformLineToUserSpaceX(double deviceSpace) {
		return deviceSpace * transformation.getScaleX()
				+ transformation.getTranslateX();
	}

	public double transformLineToUserSpaceY(double deviceSpace) {
		return deviceSpace * transformation.getScaleY()
				+ transformation.getTranslateY();
	}

	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub

		// Nakon određivanja vrednosti translateX i translateY koje
		// zavise potrebno je setovati novu transformaciju
		// uzeti u obzir koji je skrol bar pomeran
		// i u zavisnosti od prethodne pozicije datog skrol bara
		// i trenutnog skaliranja izvrsiti transformaciju translacije
		// nove
		if (e.getAdjustable().getOrientation() == Adjustable.HORIZONTAL) {
			if (hScrollValue < e.getValue()) {
				translateX += (double) ((e.getValue() - hScrollValue) * (-translateFactor))
						/ transformation.getScaleX();

			} else {
				translateX += (double) ((e.getValue() - hScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
			}
			hScrollValue = e.getValue();

		} else {
			if (vScrollValue < e.getValue()) {
				translateY += (double) ((e.getValue() - vScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
			} else {
				translateY += (double) ((e.getValue() - vScrollValue) * (-translateFactor))
						/ transformation.getScaleX();
			}
			vScrollValue = e.getValue();
		}
		setvScrollValue(vScrollValue);
		sethScrollValue(hScrollValue);
		getSbVertical().setValue(vScrollValue);
		getSbHorizontal().setValue(hScrollValue);
		setupTransformation();
		repaint();

	}

	public JScrollBar getSbVertical() {
		return sbVertical;
	}

	public void setSbVertical(JScrollBar sbVertical) {
		this.sbVertical = sbVertical;
	}

	public JScrollBar getSbHorizontal() {
		return sbHorizontal;
	}

	public void setSbHorizontal(JScrollBar sbHorizontal) {
		this.sbHorizontal = sbHorizontal;
	}

	public int gethScrollValue() {
		return hScrollValue;
	}

	public void sethScrollValue(int hScrollValue) {
		this.hScrollValue = hScrollValue;
	}

	public int getvScrollValue() {
		return vScrollValue;
	}

	public void setvScrollValue(int vScrollValue) {
		this.vScrollValue = vScrollValue;
	}

	@SuppressWarnings("unchecked")
	public void paste() {

		Transferable clipboardContent = MainFrame.getInstance().getClipboard()
				.getContents(MainFrame.getInstance());
		if ((clipboardContent != null)
				&& (clipboardContent
						.isDataFlavorSupported(DiagramElementsSelection.elementFlavor))) {
			try {
				DiagramDevice device = null;
				ArrayList<DiagramElement> tempElements = (ArrayList<DiagramElement>) clipboardContent
						.getTransferData(DiagramElementsSelection.elementFlavor);
				for (int i = 0; i < tempElements.size(); i++) {

					if (tempElements.get(i) instanceof DiagramDevice) {
						device = (DiagramDevice) tempElements.get(i).clone();
						Point2D newLocation = (Point2D) device.getPosition()
								.clone();
						newLocation.setLocation(
								device.getPosition().getX() + 40, device
										.getPosition().getY() + 40);
						device.setPosition(newLocation);

						diagram.getModel().addDiagramElement(device);

					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public JPanel getFramework() {
		return framework;
	}

	public void autoScroll(Direction direction) {

		switch (direction) {
		case Left: {
			int valueScroll = sbHorizontal.getValue();
			if (valueScroll >= sbHorizontal.getMinimum()) {
				sbHorizontal.setValue(sbHorizontal.getValue() - 2);
				valueScroll = sbHorizontal.getValue();
				hScrollValue = valueScroll;
			}
			break;
		}
		case Up: {
			int valueScroll = sbVertical.getValue();
			if (valueScroll <= sbVertical.getMaximum()) {
				sbVertical.setValue(sbVertical.getValue() + 2);
				valueScroll = sbVertical.getValue();
				vScrollValue = valueScroll;
			}
			break;
		}
		case Right: {
			int valueScroll = sbHorizontal.getValue();
			if (valueScroll <= sbHorizontal.getMaximum()) {
				sbHorizontal.setValue(sbHorizontal.getValue() + 2);
				valueScroll = sbHorizontal.getValue();
				hScrollValue = valueScroll;
			}
			break;
		}
		case Down: {
			int valueScroll = sbVertical.getValue();
			if (valueScroll <= sbVertical.getMaximum()) {
				sbVertical.setValue(sbVertical.getValue() - 2);
				valueScroll = sbVertical.getValue();
				vScrollValue = valueScroll;
			}
			break;

		}

		}
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	public void zoomIn() {
		double newScaling = scaling;

		newScaling *= scalingFactor;

		// Zatim je potrebno da skaliranje održimo u intervalu [0.1, 10]
		if (newScaling < 0.1)
			newScaling = 0.1;
		if (newScaling > 10)
			newScaling = 10;

		/*
		 * newScaling je novi parametar skaliranja (članovi m00 i m11
		 * transformacione matrice) Prilikom skaliranja dolazi do pomeranja
		 * userspace koordinata na kojima se nalazi pokazivač miša. Da bi dobili
		 * ispravan Point zoom moramo izvršiti translaciju tako da poništimo
		 * "smicanje" usled skaliranja tj. moramo postići da se userspace
		 * koordinate miša ne promene.
		 */

		Point2D oldPosition = new Point2D.Double(getWidth() / 2,
				getHeight() / 2);
		transformToUserSpace(oldPosition);

		scaling = newScaling;
		setupTransformation();

		Point2D newPosition = new Point2D.Double(getWidth() / 2,
				getHeight() / 2);
		transformToUserSpace(newPosition);

		translateX += newPosition.getX() - oldPosition.getX();
		translateY += newPosition.getY() - oldPosition.getY();

		sbVertical.setVisibleAmount((int) (20 / scaling));
		sbHorizontal.setVisibleAmount((int) (20 / scaling));

		setupTransformation();
	}

	public void zoomOut() {
		double newScaling = scaling;

		newScaling /= scalingFactor;

		// Zatim je potrebno da skaliranje održimo u intervalu [0.1, 10]
		if (newScaling < 0.1)
			newScaling = 0.1;
		if (newScaling > 10)
			newScaling = 10;

		/*
		 * newScaling je novi parametar skaliranja (članovi m00 i m11
		 * transformacione matrice) Prilikom skaliranja dolazi do pomeranja
		 * userspace koordinata na kojima se nalazi pokazivač miša. Da bi dobili
		 * ispravan Point zoom moramo izvršiti translaciju tako da poništimo
		 * "smicanje" usled skaliranja tj. moramo postići da se userspace
		 * koordinate miša ne promene.
		 */

		Point2D oldPosition = new Point2D.Double(getWidth() / 2,
				getHeight() / 2);
		transformToUserSpace(oldPosition);

		scaling = newScaling;
		setupTransformation();

		Point2D newPosition = new Point2D.Double(getWidth() / 2,
				getHeight() / 2);
		transformToUserSpace(newPosition);

		translateX += newPosition.getX() - oldPosition.getX();
		translateY += newPosition.getY() - oldPosition.getY();

		sbVertical.setVisibleAmount((int) (20 / scaling));
		sbHorizontal.setVisibleAmount((int) (20 / scaling));

		setupTransformation();
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		DiagramView diagramView = (DiagramView) e.getInternalFrame();

		Object put[] = new Object[3];
		put[0] = ((Workspace) MainFrame.getInstance().getWorkspaceModel()
				.getRoot());
		put[1] = diagramView.getDiagram().getParent();
		put[2] = diagramView.getDiagram();

		TreePath path = new TreePath(put);
		MainFrame.getInstance().getWorkspaceTree().setSelectionPath(path);

	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

}
