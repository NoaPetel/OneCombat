package camera.components;

import java.util.ArrayList;
import java.util.Iterator;

import system.CameraManager;
import system.GameSystem;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CameraZoom extends MonoBehavior {

	private CameraManager cameraManager;
	private float speedScale = 0.04f;
	private float zoomSmoothness = 0.5f;
	private float scaleMin = 0.02f;
	float cameraRate = 0.7f;

	private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	public CameraZoom(GameObject g, Transform t) {
		super(g, t);
	}

	public void start() {
		cameraManager = GameSystem.system.cameraManager;
	}

	public void update() {
		gameObjects = CameraMain.getObjectList();
		
		
		float scale = cameraManager.getScale();
		float newScale;

		// Checking if players are in the screen. If not, dezooming if not
		if (!checkInView()) {
			newScale = scale + speedScale * Time.deltaTime;
			scale = lerp(scale, newScale, zoomSmoothness);
			// Checking if players are close enough to zoom in and enhancing the viewpoint
		} else {

			float distance = distance();
			float distanceThreshold = 0.5f;

			if ((distance < distanceThreshold * cameraManager.getHeight() * scale
					&& distance < distanceThreshold * cameraManager.getWidth() * scale) && scale > scaleMin) {

				newScale = scale - speedScale * Time.deltaTime;
				scale = lerp(scale, newScale, zoomSmoothness);
			}

		}
		GameSystem.system.cameraManager.setScale(scale);
	}

	public boolean checkInView() {

		float itemWidth = 0.5f;
		float itemHeight = 0.5f;
		Iterator iter = gameObjects.iterator();
		boolean inView = true;
		while (iter.hasNext()) {
			GameObject gameObject = (GameObject) iter.next();
			if (gameObject.getRigidbody() != null) {
				Rigidbody rb = gameObject.getRigidbody();
				Transform tf = gameObject.transform;
				inView = inView && cameraManager.inSmallView(tf.getPositionX() - rb.width / 2,
						tf.getPositionY() - rb.height / 2, rb.width, rb.height, cameraRate);
			} else {
				Transform tf = gameObject.transform;
				inView = inView && cameraManager.inSmallView(tf.getPositionX() - itemWidth / 2,
						tf.getPositionY() - itemHeight / 2, itemWidth, itemHeight, cameraRate);
			}
		}

		return inView;
	}

	public float distance() {
		float maxDistance = 0;

		Object[] listGameObjects = gameObjects.toArray();

		for (int i = 0; i < listGameObjects.length; i++) {
			GameObject pivot = (GameObject) listGameObjects[i];
			for (int j = i; j < listGameObjects.length; j++) {
				float distance = distanceTwoPoints(pivot.transform, ((GameObject)listGameObjects[j]).transform);
				maxDistance = Math.max(distance, maxDistance);
			}
		}

		return maxDistance;
	}

	public float distanceTwoPoints(Transform tf1, Transform tf2) {
		float[] coords1 = { tf1.getPositionX(), tf1.getPositionY() };
		float[] coords2 = { tf2.getPositionX(), tf2.getPositionY() };

		return (float) Math.sqrt(Math.pow(coords1[0] - coords2[0], 2) + Math.pow(coords1[1] - coords2[1], 2));
	}

	/*
	 * Fonction used to make the zoom smooth
	 */
	public float lerp(float a, float b, float t) {
		return a * (1 - t) + b * t;
	}

}