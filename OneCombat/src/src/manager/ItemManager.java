package src.manager;

import java.util.ArrayList;
import java.util.Iterator;

import camera.components.CameraMain;
import camera.components.CameraMovement;
import camera.components.CameraZoom;
import system.GameSystem;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ItemManager extends MonoBehavior {
	
	public static ItemManager instance;
	private ArrayList<ItemInformation> fixedItems = new ArrayList<ItemInformation>();

	private float respawnTimer = 5f;
	private float distanceRespawnMax = 5f;

	public class ItemInformation {
		public GameObject item;
		public float timer;

		public ItemInformation(GameObject item, float time) {
			this.item = item;
			timer = time;
		}
	}

	public ItemManager(GameObject g, Transform t) {
		super(g,t);
		instance = this;

	}

	public void start() {

	}

	public void update() {
		Iterator iter = fixedItems.iterator();

		ArrayList<ItemInformation> removedObjects = new ArrayList<ItemInformation>();
		
		while (iter.hasNext()) {
			ItemInformation fixedItem = (ItemInformation) iter.next();
			if (fixedItem.timer > 0) {
				fixedItem.timer -= Time.deltaTime;
			} else {
				removedObjects.add(fixedItem);
				//fixedItems.remove(fixedItem);
				GameSystem.system.sceneManager.getCurrentScene().addGameObject(fixedItem.item);
				CameraMain.AddGameObjectCamera(fixedItem.item);
			}
		}

		
		Iterator iter2 = removedObjects.iterator();
		while(iter2.hasNext()) {
			ItemInformation removedObject = (ItemInformation) iter2.next();
			fixedItems.remove(removedObject);
		}
	}

	public void respawn(GameObject itemKilled) {
		ItemInformation item = new ItemInformation(itemKilled, respawnTimer);
		fixedItems.add(item);
	}
	
	public ArrayList<ItemInformation> getItems() {
		return fixedItems;
	}
}
