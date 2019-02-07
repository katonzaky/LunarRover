package Display.states;

import java.awt.Graphics;

import Display.Display;
import Display.icons.Rover;

public class ManualState extends State {
	private Rover rover;
	
	public ManualState(Display display, Rover rover) {
		super(display);
		this.rover = rover;
		
		display.getCamera().move(0, 0);
	}
	
	@Override
	public void tick() {
		rover.tick();
		display.getCamera().move(1, 1);
	}

	@Override
	public void render(Graphics graphics, String map) {
		rover.render(graphics,map);
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}
}
