package Display.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import Display.Display;

public class Status {
	private JPanel statusPane;
	private JButton Status;
	private JFrame log;
	private JTextArea text;
//	private JTextField Status;
	private JScrollPane scroll;
	private int x, y, width, height;
	private Display display;
	private Calendar now;
	
	public Status(Display display, int x, int y, int width, int height) {
		this.display = display;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		createStatus();
	}
	
	private void createStatus() {
		//LOG TEXT
		text = new JTextArea();
		DefaultCaret caret = (DefaultCaret)text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		text.setBackground(UIManager.getColor("Button.background"));
		text.setFont(new Font("Tahoma", Font.PLAIN, (int) (20*display.getsWidth())));
		text.setText(" Rover Logs: \n");
		text.setEditable(false);
		scroll = new JScrollPane (text,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		log = new JFrame("Rover logs");
		
		//STATUS
		Status = new JButton("  Start");
		Status.setFocusable(false);
		Status.setFocusPainted(false);
		Status.setBorderPainted(false);
		Status.setBackground(null);
		statusPane = new JPanel();
		statusPane.setBounds(x, y, width, height);
		statusPane.setBackground(Color.WHITE);
		statusPane.setLayout(null);
		Status.setBorder(null);
		Status.setFont(new Font("Tahoma", Font.BOLD, (int) (30*display.getsWidth())));
		Status.setBounds((int) (0*display.getsWidth()), (int) (0*display.getsHeight()), (int) (800*display.getsWidth()), (int) (70*display.getsHeight()));
		Status.setHorizontalAlignment(SwingConstants.LEFT);
		Status.setOpaque(false);
		statusPane.add(Status);
	}
	
	public void tick() {
		Status.setBackground(Color.GRAY);
		if(Status.getModel().isPressed()) {
			log.setVisible(true);
			log.setBounds((int) (100*display.getsWidth()), (int) (100*display.getsHeight()), (int) (800*display.getsWidth()), (int) (400*display.getsHeight()));
			log.getContentPane().add(scroll);
			log.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			log.requestFocus();
		}
	}

	public JPanel getStatusPane() {
		return statusPane;
	}
	
	public void setStatus(String x) {
		now = Calendar.getInstance();
		Status.setText("  "+x);
		text.append("\n ["+now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND)+ "]" + " " + x);
	}

}
