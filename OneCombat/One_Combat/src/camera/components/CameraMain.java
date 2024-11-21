package camera.components;

import java.util.ArrayList;
import java.util.Iterator;

import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CameraMain extends MonoBehavior {

	public static ArrayList<ObjectInformation> gameObjects = new ArrayList<ObjectInformation>();
	private static float overallTimer = 5f;

	static class ObjectInformation {
		public GameObject gameObject;
		public float timer;

		private ObjectInformation(GameObject g, float t) {
			gameObject = g;
			timer = t;
		}
	}

	public CameraMain(GameObject g, Transform t, ArrayList<GameObject> players) {
		super(g, t);
		Iterator iter = players.iterator();
		gameObjects.clear();
		for (GameObject player : players) {

			gameObjects.add(new ObjectInformation(player, -1f));
		}
	}

	public void start() {

	}

	public void update() {
		
		Iterator iter = gameObjects.iterator();
		ArrayList<ObjectInformation> removedObjects = new ArrayList<ObjectInformation>();

		int debug = 0;
		while (iter.hasNext()) {
			debug++;
			ObjectInformation object = (ObjectInformation) iter.next();
			if (object.timer == -1) {
				continue;
			} else if (object.timer > 0) {
				object.timer -= Time.deltaTime;
			} else {
				removedObjects.add(object);
			}
		}

		Iterator iter2 = removedObjects.iterator();

		while (iter2.hasNext()) {
			ObjectInformation removedObject = (ObjectInformation) iter2.next();
			gameObjects.remove(removedObject);
		}
	}

	public static void AddGameObjectCamera(GameObject g) {
		ObjectInformation object = new ObjectInformation(g, overallTimer);
		gameObjects.add(object);
	}
	
	public static void AddCharacterCamera(GameObject player) {
		ObjectInformation object = new ObjectInformation(player, -1f);
		gameObjects.add(object);
	}

	public static ArrayList<GameObject> getObjectList() {
		ArrayList<GameObject> list = new ArrayList<GameObject>();
		Iterator iter = gameObjects.iterator();
		while (iter.hasNext()) {
			ObjectInformation objectInformation = (ObjectInformation) iter.next();

			list.add(objectInformation.gameObject);
		}

		return list;
	}
}
