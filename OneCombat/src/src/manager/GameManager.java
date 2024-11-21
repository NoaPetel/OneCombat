package src.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Character.Character;
import camera.components.CameraMain;
import src.manager.gamesettings.GameSettings;
import system.GameSystem;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class GameManager extends MonoBehavior {

	public static GameManager instance;
	public static GameSettings gameSettings;
	
	public static String player1 = "Luffy";
	public static String player2 = "Luffy";
	public static String[] realPlayers = { player1, player2 };

	private static ArrayList<PlayerInformation> playerInformations = new ArrayList<PlayerInformation>();
	private static ArrayList<GameObject> players = new ArrayList<GameObject>();
	
	public static int nbCharactersAlive = 0;
	
	Random r;

	public class PlayerInformation {
		FlatVector spawnPoint;
		GameObject player;
		boolean isAlive;

		public PlayerInformation(FlatVector position, GameObject g) {
			player = g;
			isAlive = true;
		}
	}

	public GameManager(GameObject g, Transform t) {
		super(g, t);
		instance = this;
		nbCharactersAlive = 0;
		r = new Random();
	}

	public void start() {
		gameSettings = new GameSettings();

		int index = 0;
		Iterator iterPlayerInformation = playerInformations.iterator();

		while (index < gameSettings.numberPlayer) {
			Character player = Character.createPlayer(realPlayers[index], gameSettings.playerAutomates[index]);
			player.transform.setPosition(gameSettings.spawnPoint[index]);
			GameSystem.system.sceneManager.getCurrentScene().addGameObject(player);
			CameraMain.AddCharacterCamera(player);
			players.add(player);
			playerInformations.add(new PlayerInformation(gameSettings.spawnPoint[index], (GameObject) player));
			index++;
			nbCharactersAlive++;
		}
		
		for(int i = 0; i < gameSettings.numberCharacterAi; i++) {
			int position = r.nextInt(gameSettings.spawnPoint.length - 1);
			Character ai = Character.createRandomCharacterAi(gameSettings.characterAiAutomates[0]);
			ai.transform.setPosition(gameSettings.spawnPoint[position]);
			GameSystem.system.sceneManager.getCurrentScene().addGameObject(ai);
			players.add(ai);
			playerInformations.add(new PlayerInformation(gameSettings.spawnPoint[position], (GameObject) ai));
			nbCharactersAlive++;
		}

	}

	public void update() {

	}

	public static void PlayerDeath(GameObject g) {
		
		Iterator iter = playerInformations.iterator();

		while (iter.hasNext()) {

			PlayerInformation playerInformation = (PlayerInformation) iter.next();
			if (playerInformation.player == g) {

				playerInformation.player = g;
				playerInformation.isAlive = false;
				nbCharactersAlive--;
				System.out.println("Joueur mort, Joueurs restant : " + nbCharactersAlive);
				break;
			}
		}

		CheckEndGame();
	}

	public static void CheckEndGame() {
		Iterator iter = playerInformations.iterator();
		int nombrePlayer = 0;
		int nombreDead = 0;
		while (iter.hasNext()) {
			nombrePlayer++;
			PlayerInformation playerInformation = (PlayerInformation) iter.next();
			if (!playerInformation.isAlive)
				nombreDead++;
		}

		if (nombreDead >= nombrePlayer - 1) {
			System.out.println("End game");
		}
	}

	public static ArrayList<GameObject> getPlayers() {
		return players;
	}
	
	public static ArrayList<GameObject> getPlayersAlive(){
		ArrayList<GameObject> playerAlive = new ArrayList<GameObject>();
		for(PlayerInformation player : playerInformations) {
			if(player.isAlive) {
				playerAlive.add(player.player);
			}
		}
		
		return playerAlive;
	}
}
