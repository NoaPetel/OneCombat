package src.manager;

import java.io.IOException;

import src.bots.Chien.Chien;
import src.bots.MediumBot.MediumBot;
import src.bots.WeakBot.WeakBot;
import system.GameSystem;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.scene.IScene;

public class BotManager extends MonoBehavior{
	
	public static BotManager instance;
	
	//cons enum
	public static final int Chien = 1;
	public static final int Weakbot = 2;
	public static final int Mediumbot = 3;
	
	//cons max bot
	private int Max_Bot_Medium;
	private int Max_Bot_Weak;
	private int Max_Bot_Chien;
	
	//cons current number of bot
	private int Nb_Bot_Medium = 0;
	private int Nb_Bot_Weak = 0;
	private int Nb_Bot_Chien = 0;
	
	// Map information
	private int Min_x = -31;
	private int Min_y = -10;
	int WIDTH_MAP;
	int HEIGHT_MAP; 
	
	//Current Scene
	IScene sc;
	
	//Timer
	private float time = 60; //toutes les minutes
	private float timer = 0;
	
	public BotManager(GameObject g, Transform t, int WIDTH, int HEIGHT) {
		super(g,t);
		instance = this;
		this.sc = GameSystem.system.sceneManager.getCurrentScene();
		this.WIDTH_MAP = WIDTH-1;
		this.HEIGHT_MAP = HEIGHT-1;
	}
	
	
	private int getRandomint(int max, int min) {
		int difference = max-min;
		return (int) ((Math.floor(Math.random() * difference))+min);
	}
	
	
	private void random_Spawn(int Nb_Bot, int type) {
		int x_random;
		int y_random;
		int map[][] = TileManager.instance.getGrid();
		for(int i = 0; i < Nb_Bot; i ++) {
			x_random = getRandomint(this.WIDTH_MAP, 0);
			y_random = getRandomint(this.HEIGHT_MAP, 0);
			while(map[x_random][y_random] != 0 && y_random > Min_y) {
				y_random--;
			}
			if(y_random == Min_y) { // tout en haut de la map ==> forcement vide
				y_random--;
			}
			try {
				spawnType(x_random+Min_x,y_random+Min_y,type);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void spawnType(int x, int y, int type) throws IOException {
		if(type == Chien) {
			this.sc.addGameObject(new Chien(x, y));
			this.Nb_Bot_Chien++;
		}
		else if(type == Weakbot) {
			this.sc.addGameObject(new WeakBot(x, y));
			this.Nb_Bot_Weak++;
		}
		else if(type == Mediumbot) {
			this.sc.addGameObject(new MediumBot(x, y));
			this.Nb_Bot_Medium++;
		}
	}
	
	private void Nb_verification() {
		if(Nb_Bot_Chien < Max_Bot_Chien) {
			random_Spawn((Max_Bot_Chien-Nb_Bot_Chien),Chien);
		}
		if(Nb_Bot_Weak < Max_Bot_Weak) {
			random_Spawn((Max_Bot_Weak-Nb_Bot_Weak),Weakbot);
		}
		if(Nb_Bot_Medium < Max_Bot_Medium) {
			random_Spawn((Max_Bot_Medium-Nb_Bot_Medium),Mediumbot);
		}
	}
	
	public void BotDeath(int type) {
		if(type == Chien) {
			Nb_Bot_Chien--;
		}
		else if(type == Weakbot) {
			Nb_Bot_Weak--;
		}
		else if(type == Mediumbot) {
			Nb_Bot_Medium--;
		}
	}
	
	public int getNbBot(int type) {
		if(type == Chien) {
			return Nb_Bot_Chien;
		}
		else if(type == Weakbot) {
			return Nb_Bot_Weak;
		}
		else if(type == Mediumbot) {
			return Nb_Bot_Medium;
		}
		return 0;
	}
	
	public void start() {
		Max_Bot_Medium = GameManager.instance.gameSettings.maxBotMedium;
		Max_Bot_Weak = GameManager.instance.gameSettings.maxBotWeak;
		Max_Bot_Chien = GameManager.instance.gameSettings.maxBotChien;
		random_Spawn(Max_Bot_Chien, Chien);
		random_Spawn(Max_Bot_Weak, Weakbot);
		random_Spawn(Max_Bot_Medium, Mediumbot);
	}
	
	@Override
	public void update() {
		if(timer <= 0) {
			timer = time;
			Nb_verification();
		}
		else {
			timer -= Time.deltaTime;
		}
	}
	
	
}