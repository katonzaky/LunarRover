package Display.icons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import Display.Display;
import Display.gfx.Assets;

public class Rover {
	protected float x, y;
	protected float speed;
	protected float xMove, yMove;
	protected float heading = 0;
	protected float rad = 0;
	
	private Display display;
	
	public Rover(Display display, float x, float y) {
		this.x = x;
		this.y = y;
		this.display = display;
		speed = 3;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		x += xMove;
		y += yMove;
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(display.getInputManager().up) {
			yMove = -speed*(float)Math.cos(Math.toRadians(heading));
			xMove = speed*(float)Math.sin(Math.toRadians(heading));
		}
		if(display.getInputManager().down) {
			yMove = speed*(float)Math.cos(Math.toRadians(heading));
			xMove = -speed*(float)Math.sin(Math.toRadians(heading));
		}
		if(display.getInputManager().left) {
			heading -= speed;
		}
		if(display.getInputManager().right) {
			heading += speed;
		}
	}
	
	public void tick() {
		getInput();
		move();
		display.getCamera().centerOn(this);
		rad=5;
	}
	
	protected double scale = 0, scale_off = 0;
	public void render(Graphics graphics, String map) {
		scale = 1+0.2f*Math.sin(Math.toRadians(rad));
		scale_off = (Assets.icn_rover.getWidth()*scale)/2;
		AffineTransform transform;
		if(map=="Main") {
			transform = AffineTransform.getTranslateInstance(x-scale_off, y-scale_off);
		}else {
			transform = AffineTransform.getTranslateInstance(x-scale_off-display.getCamera().getXoffset(), y-scale_off-display.getCamera().getYoffset());
		}
		transform.scale(scale, scale);
		transform.rotate(Math.toRadians(heading), Assets.icn_rover.getWidth()/2, Assets.icn_rover.getHeight()/2);
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.drawImage(Assets.icn_rover, transform, null);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
