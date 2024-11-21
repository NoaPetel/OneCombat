package system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import info3.game.graphics.GameCanvas;

public class GameSystem {

	public static GameSystem system;
	public static boolean gameStarted = false;
	
	public static float windowWidth;
	public static float windowHeight;

	// Setup canvas
	private JFrame frame;
	private JLabel text;
	private long textElapsed;
	// Setup system variables
	public GameCanvas canvas;
	public Listener listener;
	public InputManager inputManager;
	public SceneManager sceneManager;
	public CameraManager cameraManager;
	public Time time;
	public Physics physics;
	public AnimationManager animationManager;
	public AutomateManager automateManager;
	public AudioManager audioManager;

	public static void main(String args[]) throws Exception {
		try {
			System.out.println("Game starting...");
			new GameSystem();
			system.start();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	GameSystem() throws Exception {
		int width = 1000;
		int height = 1000;
		system = this;
		
		//Instantation de scene du jeu ici
		sceneManager = new SceneManager();
		inputManager = new InputManager();
		listener = new Listener(this, inputManager);
		canvas = new GameCanvas(listener);
		time = new Time();
		physics = new Physics();
		animationManager = new AnimationManager();
		automateManager = new AutomateManager();
		audioManager = new AudioManager();
		cameraManager = new CameraManager(width, height);


		System.out.println("  - creating frame...");
		Dimension d = new Dimension(width, height);
		frame = canvas.createFrame(d);

		System.out.println("  - setting up the frame...");
		setupFrame();
	}
	
	public void start() {
		sceneManager.start();
		gameStarted = true;
	}

	private void setupFrame() {

		frame.setTitle("Game");
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setUndecorated(true);

		frame.add(canvas, BorderLayout.CENTER);

		text = new JLabel();
		text.setText("Tick: 0ms FPS=0");
		frame.add(text, BorderLayout.NORTH);

		// center the window on the screen
		frame.setLocationRelativeTo(null);

		// make the window visible
		frame.setVisible(true);
	}

	void update(long elapsed) {

		if(!gameStarted) return;
		
		time.SetDeltaTime(elapsed);
		sceneManager.update();
		physics.update(Constante.CURRENTITERATIONS);
		cameraManager.update();
		// Update every second
		// the text on top of the frame: tick and fps
		textElapsed += elapsed;
		if (textElapsed > 1000)

		{
			textElapsed = 0;
			float period = canvas.getTickPeriod();
			int fps = canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			text.setText(txt);
		}
		if(inputManager.IsPressed(27)) 
			if(sceneManager.getCurrentIndexScene() == 0)
				exit();
			else
				sceneManager.loadScene(0);
	}

	void render(Graphics g) {
		
		if(!gameStarted) return;
		
		windowWidth = canvas.getWidth();
		windowHeight = canvas.getHeight();
		cameraManager.setSize(windowWidth, windowHeight);

		g.setColor(Color.white);
		g.fillRect(0, 0, (int)windowWidth, (int)windowHeight);
		
		sceneManager.render(g);
		if(Constante.ISDEBUGMODE) physics.render(g);
	}
	
	public void exit() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	public void setCursor(Cursor cursor) {
		frame.setCursor(cursor);
	}
}
