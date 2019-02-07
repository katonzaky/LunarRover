package Display.icons;

import java.awt.Graphics;
import java.util.LinkedList;

import Display.Display;

public class multTarget {
	private LinkedList<Target> targets = new LinkedList<Target>();
	
	Target tmptarget;
	
	Display display;
	
	public multTarget(Display display) {
		this.display = display;
	}
	
	public void tick() {
		if(display.getInputManager().leftmouse) {
			//if(display.getInputManager().mouseX>display.getMapdisplay().getX()&&display.getInputManager().mouseX<display.getMapdisplay().getWidth()+display.getMapdisplay().getX()) {
				//if(display.getInputManager().mouseY>display.getMapdisplay().getY()&&display.getInputManager().mouseY<display.getMapdisplay().getHeight()+display.getMapdisplay().getY()) {
					addTarget(new Target(display, display.getInputManager().mouseX, display.getInputManager().mouseY));
				//}
			//}
			//addTarget(new Target(display, display.getInputManager().mouseX+display.getCamera().getXoffset(), display.getInputManager().mouseY+display.getCamera().getYoffset()));
			display.getInputManager().leftmouse = false;
		}
		for(int i=0; i<targets.size(); i++) {
			tmptarget = targets.get(i);
			tmptarget.tick();
			System.out.println("click");
			if(i>=3) {
				removeTarget(targets.get(0));
			}
		}
	}
	
	public void render(Graphics graphics, String map) {
		for(int i=0; i<targets.size(); i++) {
			tmptarget = targets.get(i);
			tmptarget.render(graphics, map);
		}
	}
	
	public void addTarget(Target t) {
		targets.add(t);
	}

	public void removeTarget(Target t) {
		targets.remove(t);
	}
}



