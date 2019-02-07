package Display.gfx;

import java.awt.image.BufferedImage;


public class Assets {

	public static BufferedImage icn_rover, icn_target;
	public static BufferedImage icn_leftpress, icn_rightpress, icn_uppress, icn_downpress; 
	public static BufferedImage icn_right, icn_left, icn_up, icn_down;
	public static BufferedImage icn_add, icn_delete, icn_destination, icn_help, icn_logo;
	public static BufferedImage icn_export, icn_import;
	
	public static void init() {
		//Load images
		icn_rover = ImageLoader.loadImage("/arrow.png");
		icn_target = ImageLoader.loadImage("/target.png");
		
		icn_leftpress = ImageLoader.loadImage("/left-pressed.png");
		icn_rightpress = ImageLoader.loadImage("/right-pressed.png");
		icn_uppress = ImageLoader.loadImage("/up-pressed.png");
		icn_downpress = ImageLoader.loadImage("/down-pressed.png");
		icn_left = ImageLoader.loadImage("/left.png");
		icn_right = ImageLoader.loadImage("/right.png");
		icn_up = ImageLoader.loadImage("/up.png");
		icn_down = ImageLoader.loadImage("/down.png");
		
		icn_add = ImageLoader.loadImage("/add.png");
		icn_delete = ImageLoader.loadImage("/delete.png");
		icn_destination = ImageLoader.loadImage("/destination.png");
		icn_help = ImageLoader.loadImage("/help.png");
		icn_export = ImageLoader.loadImage("/export.png");
		icn_import = ImageLoader.loadImage("/import.png");
		icn_logo = ImageLoader.loadImage("/rover.png");
	}
}
