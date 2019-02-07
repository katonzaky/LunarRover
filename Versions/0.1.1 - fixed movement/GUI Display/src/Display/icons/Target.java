package Display.icons;

import java.awt.Graphics;

import Display.Display;
import Display.gfx.Assets;

public class Target {
	protected float x, y;
	private Display display;
	
	public Target(Display display, float x, float y) {
		this.display = display;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {

	}
	
	public void render(Graphics graphics, String map) {
		if(map=="Main") {
			graphics.drawImage(Assets.icn_target, (int)x-Assets.icn_target.getWidth()/2, (int)y-Assets.icn_target.getHeight()/2, null);
		}else {
			graphics.drawImage(Assets.icn_target, (int)x-Assets.icn_target.getWidth()/2-(int)display.getCamera().getXoffset(), (int)y-Assets.icn_target.getHeight()/2-(int)display.getCamera().getYoffset(), null);
		}
	}
	
}
