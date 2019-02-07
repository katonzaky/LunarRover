package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Display.gfx.Assets;
import Display.gfx.Camera;
import Display.gui.Arrows;
import Display.gui.Modes;
import Display.gui.Status;
import Display.gui.Stop;
import Display.gui.Title;
import Display.gui.Tools;
import Display.icons.Rover;
import Display.input.InputManager;
import Display.map.Legend;
import Display.map.MapDisplay;
import Display.states.AutoState;
import Display.states.ManualState;
import Display.states.State;


public class Display implements Runnable {
	private MapDisplay mapdisplay;
	private MapDisplay miniMap;
	private Title titleBar;
	private Arrows arrowKeys;
	private Tools Toolbar;
	private Status statusBar;
	private Legend legend;
	private Modes modes;
	private Stop eStop;
	
	private double sWidth, sHeight;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy mainBStrategy;
	private BufferStrategy miniBStrategy;
	private Graphics Maingraphics;
	private Graphics Minigraphics;
	
	private Rover rover;
	
	//States
	private State manualState;
	private State autoState;
	
	//Input
	private InputManager inputManager;
	
	//Camera
	private Camera camera;
	
	public Display(String title, double sWidth, double sHeight) {
		this.sWidth = sWidth;
		this.sHeight = sHeight;
		this.title = title;
		inputManager = new InputManager();
	}
	
	private JPanel contentPane;
	private JFrame Frame;
	
	private void init() {
		Assets.init();
		
		//Creates Frame
		Frame = new JFrame("Lunar Rover Controller");
		Frame.setFocusable(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.addKeyListener(inputManager);
		
		//Creates content pane
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder((int) (5*sWidth), (int) (5*sHeight), (int) (5*sWidth), (int) (5*sHeight)));
		Frame.setContentPane(contentPane);
		contentPane.setLayout(null);
	
		//Creates map display
		mapdisplay = new MapDisplay((int)(20*sWidth), (int)(190*sHeight), (int)(800*sWidth), (int)(700*sHeight));
		mapdisplay.getCanvas().addMouseListener(inputManager);
		mapdisplay.getCanvas().addMouseMotionListener(inputManager);
		contentPane.add(mapdisplay.getCanvas());

		//Create mini map
		camera = new Camera(this, 0,0);
		miniMap = new MapDisplay((int)(830*sWidth), (int)(190*sHeight), (int)(400*sWidth), (int)(350*sHeight));
		contentPane.add(miniMap.getCanvas());
		
		//Creates Legend
		legend = new Legend((int)(830*sWidth), (int)(550*sHeight), (int)(400*sWidth), (int)(420*sHeight));
		contentPane.add(legend.getLegend());
		
		//Title bar
		titleBar = new Title(this, (int)(0*sWidth), (int)(0*sHeight), (int)(1930*sWidth), (int)(115*sHeight));
		contentPane.add(titleBar.getTitlePanel());
		
		//Arrow keys
		arrowKeys = new Arrows(this, (int)(1420*sWidth), (int)(700*sHeight), (int)(400*sWidth), (int)(270*sHeight));
		contentPane.add(arrowKeys.getArrowKeys());
		
		//Toolbar panel
		Toolbar = new Tools(this, (int)(20*sWidth), (int)(120*sHeight), (int)(800*sWidth), (int)(70*sHeight));
		contentPane.add(Toolbar.getToolbar());
		
		//Status panel
		statusBar = new Status(this, (int)(20*sWidth), (int)(900*sHeight), (int)(800*sWidth), (int)(70*sHeight));
		contentPane.add(statusBar.getStatusPane());
		
		//Modes
		modes = new Modes(this, (int)(1420*sWidth), (int)(520*sHeight), (int)(410*sWidth), (int)(190*sHeight));
		contentPane.add(modes.getModes());
		
		//Stop
		eStop = new Stop(this, (int)(1420*sWidth), (int)(305*sHeight), (int)(410*sWidth), (int)(175*sHeight));
		contentPane.add(eStop.geteStop());
		
		rover = new Rover(this, (int)(800*sWidth/2), (int)(700*sHeight/2));
		manualState = new ManualState(this, rover);
		autoState = new AutoState(this, rover);
		State.setState(manualState);
		//Frame.setUndecorated(true);
		Frame.setVisible(true);
		Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void tick() {
		if(modes.state == "Manual" && eStop.getState() == 0)
		{
			inputManager.tick();
		}
		arrowKeys.tick();
		Toolbar.tick();
		statusBar.tick();
		modes.tick();
		eStop.tick();
		
		if(State.getState()!=null) {
			State.getState().tick();			
		}
		
		if(modes.state=="Auto" && State.getState()!=autoState) {
				autoState = new AutoState(this, manualState.getRover());
				State.setState(autoState);
		}else if(modes.state=="Manual" && State.getState()!=manualState){
				manualState = new ManualState(this, autoState.getRover());
				State.setState(manualState);
		}
	}
	
	public void render() {
		mainBStrategy = mapdisplay.getCanvas().getBufferStrategy();
		miniBStrategy = miniMap.getCanvas().getBufferStrategy();
		if(mainBStrategy==null || miniBStrategy==null) {
			mapdisplay.getCanvas().createBufferStrategy(3);
			miniMap.getCanvas().createBufferStrategy(3);
			return;
		}
		Maingraphics = mainBStrategy.getDrawGraphics();
		Minigraphics = miniBStrategy.getDrawGraphics();
		
		//Clear Screen
		Maingraphics.clearRect(0, 0, (int)(800*sWidth), (int)(700*sHeight));
		Minigraphics.clearRect(0, 0, (int)(400*sWidth), (int)(350*sHeight));
		
		//Draw Here
		if(State.getState()!=null) {
			State.getState().render(Maingraphics,"Main");
			State.getState().render(Minigraphics,"Mini");
		}
		//Draw End
		mainBStrategy.show();
		miniBStrategy.show();
		
		Maingraphics.dispose();
		Minigraphics.dispose();
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1e9/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta>=1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer>=1e9) {
				//System.out.println("Ticks and Frames: "+ticks);
				ticks=0;
				timer=0;
			}
		}
		stop();
	}
	
	public InputManager getInputManager() {
		return inputManager;
	}
	
	public JFrame getFrame() {
		return Frame;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public double getsWidth() {
		return sWidth;
	}
	
	public double getsHeight() {
		return sHeight;
	}
	
	public MapDisplay getMapdisplay() {
		return mapdisplay;
	}

	public Arrows getArrowKeys() {
		return arrowKeys;
	}
	
	public Modes getModes() {
		return modes;
	}
	
	public Status getStatus() {
		return statusBar;
	}
	
	public Rover getRover() {
		return rover;
	}

	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public  synchronized void stop() {
		if(!running) {
			return;
		}
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
