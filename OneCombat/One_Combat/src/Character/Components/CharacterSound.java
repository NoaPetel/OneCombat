package Character.Components;

import java.util.Random;

import system.AudioManager;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CharacterSound extends MonoBehavior {
	
	private String folderPath = "ressources/Sounds/";
	private String path;
	private String soundName;
	private String extension = ".ogg";
	private int id;
	
	public CharacterSound(GameObject g, Transform t, String soundName) {
		super(g, t);
		this.soundName = soundName;
		this.path = folderPath + this.soundName + "Sounds/";
		Random r = new Random();
		this.id = r.nextInt(1000);
	}
	
	public void playSound(String name) {
		AudioManager.playSound(soundName + name + id, path + name + extension);
	}
	
	public void attack() {
		playSound("Attack");
		playSound("AttackVoice");
	}
	
	public void longAttack() {
		playSound("LongAttack");
		playSound("LongAttackVoice");
	}
	
	public void specialAttack() {
		playSound("SpecialAttack");
		playSound("SpecialAttackVoice");
	}
	
	public void hit() {
		playSound("HitVoice");
	}
	
	public void death() {
		playSound("DeathVoice");
	}
	
	public void dash() {
		playSound("Dash");
	}

}
