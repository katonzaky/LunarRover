package Display.map;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

public class MapDisplay {
	private Canvas canvas;
	
	private int x, y, width, height;

	public MapDisplay(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
		createDisplay();
	}
	
	private void createDisplay() {
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(x, y, width, height);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}	
}
