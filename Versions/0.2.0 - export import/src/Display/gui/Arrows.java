package Display.gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Display.Display;
import Display.gfx.Assets;
import communications.Communications;

public class Arrows {
	private JPanel arrowKeys;
	public JButton left, right, up, down;
	private int x, y, width, height;
	private Display display;
	private Image active[];
	private Image inactive[];
	private int stateL = 0, stateR = 0, stateU = 0, stateD = 0;
	private int clicks = 0;
	
	public Arrows(Display display, int x, int y, int width, int height) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		active = new Image[4];
		inactive = new Image[4];
		
		active[0] = Assets.icn_leftpress.getScaledInstance((int)(Assets.icn_leftpress.getHeight()*display.getsHeight()), (int) (Assets.icn_leftpress.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		active[1] = Assets.icn_rightpress.getScaledInstance((int)(Assets.icn_rightpress.getHeight()*display.getsHeight()), (int) (Assets.icn_rightpress.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		active[2] = Assets.icn_uppress.getScaledInstance((int)(Assets.icn_uppress.getHeight()*display.getsHeight()), (int) (Assets.icn_uppress.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		active[3] = Assets.icn_downpress.getScaledInstance((int)(Assets.icn_downpress.getHeight()*display.getsHeight()), (int) (Assets.icn_downpress.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);

		inactive[0] = Assets.icn_left.getScaledInstance((int)(Assets.icn_left.getHeight()*display.getsHeight()), (int) (Assets.icn_left.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		inactive[1] = Assets.icn_right.getScaledInstance((int)(Assets.icn_right.getHeight()*display.getsHeight()), (int) (Assets.icn_right.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		inactive[2] = Assets.icn_up.getScaledInstance((int)(Assets.icn_up.getHeight()*display.getsHeight()), (int) (Assets.icn_up.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		inactive[3] = Assets.icn_down.getScaledInstance((int)(Assets.icn_down.getHeight()*display.getsHeight()), (int) (Assets.icn_down.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);

		createKeys();
	}
	
	private void createKeys() {
		//Arrow key panel
		arrowKeys = new JPanel();
		arrowKeys.setBackground(null);
		arrowKeys.setBounds(x, y, width, height);
		arrowKeys.setLayout(null);
		
		//Left Key
		left = new JButton("");
		left.setBackground(Color.DARK_GRAY);
		left.setIcon(new ImageIcon(inactive[0]));
		left.setToolTipText("Turn Left");
		left.setBounds((int) (0*display.getsWidth()), (int) (135*display.getsHeight()), (int) (130*display.getsWidth()), (int) (130*display.getsHeight()));
		left.setContentAreaFilled(false);
		left.setBorderPainted(false);
		left.setOpaque(false);
		left.setFocusable(false);
		arrowKeys.add(left);
		
		//Right Key
		right = new JButton("");
		right.setBackground(null);
		right.setIcon(new ImageIcon(inactive[1]));
		right.setToolTipText("Turn Right");
		right.setBounds((int) (270*display.getsWidth()), (int) (135*display.getsHeight()), (int) (130*display.getsWidth()), (int) (130*display.getsHeight()));
		right.setContentAreaFilled(false);
		right.setBorderPainted(false);
		right.setOpaque(false);
		right.setFocusable(false);
		arrowKeys.add(right);
		
		//Up Key
		up = new JButton("");
		up.setBackground(null);
		up.setIcon(new ImageIcon(inactive[2]));
		up.setToolTipText("Forward");
		up.setBounds((int) (135*display.getsWidth()), (int) (0*display.getsHeight()), (int) (130*display.getsWidth()), (int) (130*display.getsHeight()));
		up.setContentAreaFilled(false);
		up.setBorderPainted(false);
		up.setOpaque(false);
		up.setFocusable(false);
		arrowKeys.add(up);
		
		//Down Key
		down = new JButton("");
		down.setBackground(null);
		down.setIcon(new ImageIcon(inactive[3]));
		down.setToolTipText("Go Back");
		down.setBounds((int) (135*display.getsWidth()), (int) (135*display.getsHeight()), (int) (130*display.getsWidth()), (int) (130*display.getsHeight()));
		down.setContentAreaFilled(false);
		down.setBorderPainted(false);
		down.setOpaque(false);
		down.setFocusable(false);
		arrowKeys.add(down);
	}
	
	public void tick() {
		if(display.getModes().getState() == "Manual")
		{		
			
			//Right key
			if(right.getModel().isPressed() || display.getInputManager().right) {
				right.setIcon(new ImageIcon(active[1]));
				display.getInputManager().right = true;
				if(stateR == 0)
				{
					Communications.send("move,right");
					//System.out.println("Moving Right");
					stateR = 1;
					clicks++;
				}
			}else {
				if(stateR == 1)
				{
					stateR = 0;
					stop();
				}
				right.setIcon(new ImageIcon(inactive[1]));
			}
			
			//Left key
			if(left.getModel().isPressed() || display.getInputManager().left) {
				left.setIcon(new ImageIcon(active[0]));
				display.getInputManager().left = true;
				if(stateL == 0)
				{
					Communications.send("move,left");
					//System.out.println("Moving Left");
					stateL = 1;
					clicks++;
				}
			}else {
				if(stateL == 1)
				{
					stateL = 0;
					stop();
				}
				left.setIcon(new ImageIcon(inactive[0]));
			}
			
			//Up key
			if(up.getModel().isPressed() || display.getInputManager().up) {
				up.setIcon(new ImageIcon(active[2]));
				display.getInputManager().up = true;
				if(stateU == 0)
				{
					Communications.send("move,up");
					//System.out.println("Moving Forward");
					stateU = 1;
					clicks++;
				}
			}else {
				if(stateU == 1)
				{
					stateU = 0;
					stop();
				}
				up.setIcon(new ImageIcon(inactive[2]));
			}
			
			//Down key
			if(down.getModel().isPressed() || display.getInputManager().down) {
				down.setIcon(new ImageIcon(active[3]));
				display.getInputManager().down = true;
				if(stateD == 0)
				{
					Communications.send("move,down");
					//System.out.println("Moving Down");
					stateD = 1;
					clicks++;
				}
			}else {
				if(stateD == 1)
				{
					stateD = 0;
					stop();
				}
				down.setIcon(new ImageIcon(inactive[3]));
			}
		}
	}

	public JPanel getArrowKeys() {
		return arrowKeys;
	}
	
	public void getAutoMode()
	{
		left.setEnabled(false);
		up.setEnabled(false);
		right.setEnabled(false);
		down.setEnabled(false);
		return;
	}
	
	public void getManualMode()
	{
		left.setEnabled(true);
		up.setEnabled(true);
		right.setEnabled(true);
		down.setEnabled(true);
		return;
	}
	
	public void disable(String x)
	{
		if(x == "up")
		{
			left.setEnabled(false);
			right.setEnabled(false);
			down.setEnabled(false);
		}
		else if(x == "down")
		{
			left.setEnabled(false);
			up.setEnabled(false);
			right.setEnabled(false);
		}
		else if(x == "left")
		{
			up.setEnabled(false);
			right.setEnabled(false);
			down.setEnabled(false);
		}
		else if(x == "right")
		{
			left.setEnabled(false);
			up.setEnabled(false);
			down.setEnabled(false);
		}
	}
	
	public void stop()
	{
		clicks--;
		if(clicks == 0)
		{
			Communications.send("move,stop");
			getManualMode();
			//System.out.println("Moving Stopped");
		}
	}


}
