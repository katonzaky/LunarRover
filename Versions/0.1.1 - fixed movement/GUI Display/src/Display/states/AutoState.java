package Display.states;

import java.awt.Graphics;

import Display.Display;
import Display.icons.Rover;
import Display.icons.multTarget;

public class AutoState extends State {
	private Rover rover;
	private multTarget mtarget;

	public AutoState(Display display, Rover rover) {
		super(display);
		
		this.rover = rover;
		mtarget = new multTarget(display);
		display.getCamera().move(0, 0);
	}

	@Override
	public void tick() {
		rover.tick();
		mtarget.tick();
		display.getCamera().move(1, 1);
	}

	@Override
	public void render(Graphics graphics, String map) {
		mtarget.render(graphics, map);
		rover.render(graphics, map);
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}
}
