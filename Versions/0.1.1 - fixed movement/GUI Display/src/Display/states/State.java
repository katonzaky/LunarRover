package Display.states;

import java.awt.Graphics;

import Display.Display;
import Display.icons.Rover;


public abstract class State {
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	protected Display display;
	
	public State(Display display) {
		this.display = display;
	}

	public abstract void tick();
	public abstract void render(Graphics graphics, String map);

	public Rover getRover() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
