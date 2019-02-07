package Display;

import java.awt.Dimension;
import java.awt.Toolkit;

public class initDisplay {
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double width = screenSize.getWidth();
	static double height =  screenSize.getHeight();
	static double sWidth = width/1920;
	static double sHeight = height/1080;
	
	public static void main(String[] args) {
		Display display = new Display("Lunar Rover Controller",sWidth, sHeight);
		display.start();
	}
}
