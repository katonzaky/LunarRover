package Display.map;

import java.awt.Color;

import javax.swing.JPanel;

public class Legend {
	private JPanel legend;
	private int x, y, width, height;
	
	public Legend(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
		createLegend();
	}
	
	private void createLegend() {
		legend = new JPanel();
		legend.setFocusable(false);
		legend.setBackground(Color.WHITE);
		legend.setBounds(x, y, width, height);
		legend.setLayout(null);
	}

	public JPanel getLegend() {
		return legend;
	}
}
