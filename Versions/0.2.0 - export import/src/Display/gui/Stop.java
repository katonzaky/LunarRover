package Display.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Display.Display;

public class Stop {
	private JPanel eStop;
	private JLabel eStatus;
	private JButton Stop;
	private int x, y, width, height;
	private Display display;
	private int state = 0;
	private Timer timer;
	
	public Stop(Display display, int x, int y, int width, int height) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		createStop();
	}
	
	private void createStop() {
		eStop = new JPanel();	
		eStop.setBounds(x, y, width, height);
		eStop.setFocusable(false);
		eStop.setBackground(Color.LIGHT_GRAY);
		eStop.setLayout(null);
		
		eStatus = new JLabel("EMERGENCY: OFF");
		eStatus.setFont(new Font("Tahoma", Font.BOLD,(int) (24* display.getsWidth())));
		eStatus.setForeground(Color.BLACK);
		eStatus.setBounds((int) (0*display.getsWidth()), (int) (5*display.getsHeight()), (int) (410*display.getsWidth() ), (int) (105*display.getsHeight()));
		eStop.add(eStatus);
		
		//STOP BUTTON
		Stop = new JButton("STOP!");
		Stop.setFocusable(false);
		Stop.setHorizontalTextPosition(SwingConstants.RIGHT);
		Stop.setToolTipText("Emergency mode");
		Stop.setBackground(new Color(139, 0, 0));
		Stop.setForeground(new Color(255, 255, 255));
		Stop.setFont(new Font("Tahoma", Font.BOLD, (int) (41*display.getsWidth())));
		Stop.setFocusPainted(false);
		Stop.setBorderPainted(false);
		Stop.setBounds((int) (0*display.getsWidth()), (int) (70*display.getsHeight()), (int) (410*display.getsWidth() ), (int) (105*display.getsHeight()));
		eStop.add(Stop);
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state++;
				if(state == 0)
				{
					display.getStatus().setStatus("Emergency mode disengaged");
					eStatus.setText("EMERGENCY: OFF");
					

				}
				else if(state == 1)
				{
					display.getStatus().setStatus("Emergency mode engaged");
					eStatus.setText("EMERGENCY: ON");
				}
				else if(state == 2)
				{
					timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							if(state == 2)
							{
								state = 1;
								display.getStatus().setStatus("Emergency mode engaged - timer");
								eStatus.setText("EMERGENCY: ON");
							}
						}
					}, 1*1000);
				}
				else if(state == 3)
				{
					timer.cancel();
					display.getStatus().setStatus("Emergency mode disengaged");
					eStatus.setText("EMERGENCY: OFF");
					state = 0;
				}
			}
		});		
	}
	
	public void tick() {
		Stop.setBackground(new Color(139, 0, 0));
		Stop.setForeground(new Color(255, 255, 255));
	}

	public JPanel geteStop() {
		return eStop;
	}
	
	public int getState() {
		return state;
	}
}
