package src.platform.actions;

import java.io.IOException;

import src.manager.PlatformManager;
import src.platform.BasicPlatform;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class PlatformSplit extends MonoBehavior{
	
	public PlatformSplit(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
	}
	
	public void splitPlatform() {
		try {
			float positionX = this.transform.getPositionX();
			float positionY = this.transform.getPositionY();
			BasicPlatform initialPlatform = ((BasicPlatform) gameObject);
			
			float newPositionX = this.transform.getPositionX() + (initialPlatform.width/2);
			PlatformManager.instance.generateRandomPlatformAtPos(positionX, positionY, initialPlatform.width/2, initialPlatform.height);
			PlatformManager.instance.generateRandomPlatformAtPos(newPositionX, positionY, initialPlatform.width/2, initialPlatform.height);
			PlatformManager.instance.currentActivePlatform++;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
