package Character.Components;

import java.util.ArrayList;

import src.manager.GameManager;
import src.manager.ItemManager;
import src.manager.ItemManager.ItemInformation;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;



public class CharacterAiHandler extends MonoBehavior {
	
	public float timePathFinding = 2f;
	public float onTimePathFinding;

	public CharacterAiHandler(GameObject g, Transform t) {
		super(g, t);
	}

	public void update() {
		this.onTimePathFinding -= Time.deltaTime;
	}
	
	
	public ArrayList<GameObject> getCharacters() {
		ArrayList<GameObject> charactersAlive = new ArrayList<GameObject>();
		ArrayList<GameObject> characters = GameManager.instance.getPlayers();
		for (GameObject c : characters) {
			if (c != gameObject) {
				CharacterHealth characterHealth = c.getComponent(CharacterHealth.class);
				if (characterHealth != null) {
					if (characterHealth.isAlive()) {
						charactersAlive.add(c);
					}
				}
			}
		}
		return charactersAlive;
	}

	public FlatVector getClosestCharacterPos() {
		FlatVector me = transform.getPosition();
		ArrayList<GameObject> characters = getCharacters();
		float closestModule = 10000f;
		FlatVector closest = new FlatVector(0f, 0f);
		String name ="rien";
		float a;
		for (GameObject character : characters) {
			FlatVector vector = character.transform.position;
			a = module(me, vector);
			if (a < closestModule) {
				closest = vector;
				closestModule = a;
			}

		}

		return closest;
	}

	public boolean isClose() {
		return module(transform.position, getClosestCharacterPos()) < 3;
	}

	public float module(FlatVector a, FlatVector b) {
		double c = Math.pow(a.x - b.x, 2);
		double d = Math.pow(a.y - b.y, 2);
		return (float) Math.sqrt(c + d);
	}

	public ArrayList<ItemInformation> getItems() {
		return ItemManager.instance.getItems();
	}

	public GameObject getClosestItem() {
		ArrayList<ItemInformation> items = getItems();
		// Todo find the closest item with this list

		if (items.size() > 0) {
			return items.get(0).item;
		}
		
		return null;
	}
	
	public void onStartPathFinding() {
		this.onTimePathFinding = this.timePathFinding;
		
	}
	
	public boolean isBloquedCheck() {
		return onTimePathFinding < 0;
	}

}
