package Display.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Display.Display;
import Display.gfx.Assets;

public class Title {
	private JPanel titlePanel;
	private JLabel Title, roverIcon;
	private Image scaledImg;
	
	private Display display;
	private int x, y, width, height;

	public Title(Display display, int x, int y, int width, int height) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
		createTitle();
	}
	
	private void createTitle() {
		//Panel
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(70, 130, 180));
		titlePanel.setBounds(x, y, width, height);
		titlePanel.setLayout(null);
		
		//Label
		Title = new JLabel("Lunar Rover Controller");
		Title.setBounds((int)(770*display.getsWidth()),(int)(0*display.getsHeight()),(int)(1070*display.getsWidth()),(int)(115*display.getsHeight()));
		titlePanel.add(Title);
		Title.setHorizontalTextPosition(JLabel.LEFT);
		Title.setVerticalTextPosition(JLabel.BOTTOM);
		Title.setIcon(null);
		Title.setForeground(Color.BLACK);
		Title.setFont(new Font("Copperplate Gothic Light", Font.BOLD,(int)(65*display.getsWidth())));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Icon
		roverIcon = new JLabel("");
		roverIcon.setBounds((int) (1740*display.getsWidth()), (int) (0*display.getsHeight()), (int) (190*display.getsWidth()), (int) (115*display.getsHeight()));
		titlePanel.add(roverIcon);
		scaledImg = Assets.icn_logo.getScaledInstance((int)(Assets.icn_logo.getHeight()*display.getsHeight()), (int) (Assets.icn_logo.getWidth()*display.getsWidth()), Image.SCALE_SMOOTH);
		roverIcon.setIcon(new ImageIcon(scaledImg));
		roverIcon.setVerticalTextPosition(SwingConstants.BOTTOM);
		roverIcon.setHorizontalTextPosition(SwingConstants.LEFT);
		roverIcon.setHorizontalAlignment(SwingConstants.CENTER);
		roverIcon.setForeground(Color.BLACK);
		roverIcon.setFont(new Font("Franklin Gothic Demi", Font.BOLD , (int) (65*display.getsWidth())));
	}

	public JPanel getTitlePanel() {
		return titlePanel;
	}
	
}
