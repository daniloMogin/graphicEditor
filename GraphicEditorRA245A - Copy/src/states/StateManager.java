package states;

import java.io.Serializable;

import workspace.view.DiagramView;

public class StateManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;

	private State currentState;

	private SelectState selectState;
	private CircleState circleState;
	private RectangleState rectangleState;
	private TriangleState triangleState;
	private StarState starState;
	private LassoState lassoState;
	private ResizeState resizeState;
	private MoveState moveState;

	public StateManager(DiagramView med) {

		selectState = new SelectState(med);
		circleState = new CircleState(med);
		rectangleState = new RectangleState(med);
		triangleState = new TriangleState(med);
		starState = new StarState(med);
		lassoState = new LassoState(med);
		resizeState = new ResizeState(med);
		moveState = new MoveState(med);
		
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

	public void setLassoState() {
		currentState = lassoState;
	}
	
	public void setResizeState() {
		currentState = resizeState;
	}

	public void setMoveState() {
		currentState = moveState;
	}

	public State getCurrentState() {
		return currentState;
	}

}
