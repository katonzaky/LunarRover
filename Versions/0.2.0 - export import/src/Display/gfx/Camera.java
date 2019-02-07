package Display.gfx;

import Display.Display;
import Display.icons.Rover;

public class Camera {
	private float xoffset, yoffset;
	private Display display;
	
	public Camera(Display display, float xoffset, float yoffset) {
		this.display = display;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
	}

	public void centerOn(Rover rover){
		//Currently set for mini map
		xoffset = rover.getX() - (int)(400*display.getsWidth()/2);
		yoffset = rover.getY() - (int)(350*display.getsHeight()/2);
	}
	public void move(float xAmt, float yAmt) {
		xoffset += xAmt;
		yoffset += yAmt;
	}
	
	public float getXoffset() {
		return xoffset;
	}

	public void setXoffset(float xoffset) {
		this.xoffset = xoffset;
	}

	public float getYoffset() {
		return yoffset;
	}

	public void setYoffset(float yoffset) {
		this.yoffset = yoffset;
	}
	
}
