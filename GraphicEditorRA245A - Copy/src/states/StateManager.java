package states;

import java.io.Serializable;

import workspace.view.DiagramView;

public class StateManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2833525174093325696L;

	private State currentState;

	private SelectState selectState;
	private CircleState circleState;
	private RectangleState rectangleState;
	private TriangleState triangleState;
	private StarState starState;
	private ResizeState resizeState;
	private MoveState moveState;
	private LassoState lassoState;

	public StateManager(DiagramView med) {

		selectState = new SelectState(med);
		circleState = new CircleState(med);
		rectangleState = new RectangleState(med);
		triangleState = new TriangleState(med);
		starState = new StarState(med);
		resizeState = new ResizeState(med);
		moveState = new MoveState(med);
		lassoState = new LassoState(med);

		currentState = selectState;
	}

	public void setSelectState() {
		currentState = selectState;
	}

	public void setCircleState() {
		currentState = circleState;
	}

	public void setRectangleState() {
		currentState = rectangleState;
	}

	public void setTriangleState() {
		currentState = triangleState;
	}

	public void setStarState() {
		currentState = starState;
	}

	public void setResizeState() {
		currentState = resizeState;
	}

	public void setMoveState() {
		currentState = moveState;
	}

	public void setLassoState() {
		currentState = lassoState;
	}

	public State getCurrentState() {
		return currentState;
	}

}
