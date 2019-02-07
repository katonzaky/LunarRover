package Display.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Display.Display;
import communications.Communications;

public class Modes {
	private JPanel modes;
	private JButton Manual, Auto, Return;
	private int x, y, width, height;
	private Display display;
	public String state;
	
	public Modes(Display display, int x, int y, int width, int height) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		state = "Manual";
		createModes();
	}
	
	private void createModes() {
		modes = new JPanel();
		modes.setBounds(x, y, width, height);
		modes.setFocusable(false);
		modes.setLayout(null);
		modes.setBackground(Color.LIGHT_GRAY);
		
		//MANUAL BUTTON
		Manual = new JButton("Manual");
		Manual.setToolTipText("Turn on Manual mode");
		Manual.setForeground(Color.GRAY);
		Manual.setFont(new Font("Tahoma", Font.BOLD, (int) (41*display.getsWidth())));
		Manual.setBackground(SystemColor.controlShadow);
		Manual.setFocusPainted(false);
		Manual.setBorderPainted(false);
		Manual.setFocusable(false);
		Manual.setBounds((int) (0*display.getsWidth()), (int) (-10*display.getsHeight()), (int) (200*display.getsWidth()), (int) (85*display.getsHeight()));
		Manual.setEnabled(false);
		modes.add(Manual);
		
		//AUTO BUTTON
		Auto = new JButton("Auto");
		Auto.setToolTipText("Turn on Auto mode");
		Auto.setForeground(Color.WHITE);
		Auto.setFont(new Font("Tahoma", Font.BOLD, (int) (41 * display.getsWidth())));
		Auto.setBackground(SystemColor.controlDkShadow);
		Auto.setFocusPainted(false);
		Auto.setBorderPainted(false);
		Auto.setFocusable(false);
		Auto.setBounds((int) (210*display.getsWidth()), (int) (-10*display.getsHeight()), (int) (200*display.getsWidth()), (int) (85*display.getsHeight()));
		Auto.setEnabled(true);
		modes.add(Auto);
		
		//RETURN BUTTON
		Return = new JButton("Return");
		
//DELETE THIS
		Communications.connectToServer("10.0.1.1", 9090);
		Return.setToolTipText("Return to landing site");
		Return.setForeground(new Color(255, 255, 255));
		Return.setFont(new Font("Tahoma", Font.BOLD, (int) (41*display.getsWidth())));
		Return.setBackground(SystemColor.controlDkShadow);
		Return.setBounds((int) (45*display.getsWidth()), (int) (80*display.getsHeight()), (int) (330*display.getsWidth()), (int) (85*display.getsHeight()));
        Return.setFocusPainted(false);
        Return.setBorderPainted(false);
        Return.setFocusable(false);
		modes.add(Return);
		
		display.getArrowKeys().getManualMode();
		state = "Manual";
		
		Manual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manual.setBackground(SystemColor.controlShadow);
				Manual.setForeground(Color.gray);
				Manual.setEnabled(false);
				
				Auto.setBackground(SystemColor.controlDkShadow);
				Auto.setForeground(Color.WHITE);
				Auto.setEnabled(true);
				display.getArrowKeys().getManualMode();
				
				display.getStatus().setStatus("Manual mode enabled");
				state = "Manual";
			}
		});	
		
		Auto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manual.setBackground(SystemColor.controlDkShadow);
				Manual.setForeground(Color.WHITE);
				Manual.setEnabled(true);
				
				Auto.setBackground(SystemColor.controlShadow);
				Auto.setForeground(Color.gray);
				Auto.setEnabled(false);
				display.getArrowKeys().getAutoMode();
				
				display.getStatus().setStatus("Auto mode enabled");
				state = "Auto";
			}
		});	

		
		Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.getStatus().setStatus(Communications.receive());
				display.getStatus().setStatus("Returning to landing site");
			}
		});	
	}
	
	public void tick() {
	}

	public JPanel getModes() {
		return modes;
	}
	
	public String getState() {
		return state;
	}

}
