package camera.components;

import java.util.ArrayList;
import java.util.Iterator;

import system.CameraManager;
import system.GameSystem;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class CameraMovement extends MonoBehavior {

	private CameraManager cameraManager;
	private float movementSmoothness = 0.005f;

	private static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private float focusTime = 5f;

	public CameraMovement(GameObject g, Transform t) {
		super(g, t);
	}

	@Override
	public void start() {
		cameraManager = GameSystem.system.cameraManager;
	}

	@Override
	public void update() {
		
		gameObjects = CameraMain.getObjectList();
		FlatVector actualPosition = cameraManager.getPosition();
		// We're centering the camera according to the average position of both players and objects selected 

		FlatVector expectedPosition = averageCameraPos();
		//System.out.println(expectedPosition.x + " " + expectedPosition.y);
		FlatVector smoothPosition = FlatVector.lerp(actualPosition, expectedPosition, movementSmoothness);
		//System.out.println(smoothPosition.x + " " + smoothPosition.y);
		cameraManager.setPos(smoothPosition.x, smoothPosition.y);

	}

	public FlatVector averageCameraPos() {
		float averageX = 0;
		float averageY = 0;
		int length = 0;

		
		Iterator iter = gameObjects.iterator();
		while (iter.hasNext()) {
			GameObject gameObject = (GameObject) iter.next();
			length++;

			averageX += gameObject.transform.getPositionX();
			averageY += gameObject.transform.getPositionY();
		}

		FlatVector position = FlatVector.div(new FlatVector(averageX, averageY), length);
		return position;
	}

}