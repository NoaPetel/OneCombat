package camera;

import java.util.ArrayList;

import camera.components.CameraMain;
import camera.components.CameraMovement;
import camera.components.CameraShake;
import camera.components.CameraZoom;
import src.manager.GameManager;
import system.object.gameobject.GameObject;

public class CameraObject extends GameObject {

	public CameraObject() {
		super();
		ArrayList<GameObject> players = GameManager.instance.getPlayers();
		components.add(new CameraMain(this, transform, players));
		components.add(new CameraMovement(this, transform));
		components.add(new CameraZoom(this, transform));
		components.add(new CameraShake(this, transform));

	}

}