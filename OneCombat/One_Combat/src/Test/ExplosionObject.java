package Test;

import system.GameSystem;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ExplosionObject extends GameObject {
	
	int exlopdeObjects = 20;
	
	public ExplosionObject(FlatVector position) {
		super();
		
		for(int i = 0; i < exlopdeObjects; i++) {
			GameSystem.system.sceneManager.getCurrentScene().addGameObject(new BasicSquareObject(position));
		}
	}
}
