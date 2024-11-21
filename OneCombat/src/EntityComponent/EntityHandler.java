package EntityComponent;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class EntityHandler extends MonoBehavior {
	
	
	private float maxPosY = 25f;
	private float minPosY = -15f;
	private float spawnY =  -14f;
	private float minPosX = -30f;
	private float maxPosX = 30f;
	private float minSpawnX =  -29f;
	private float manSpawnX =  29f;
	private boolean isKeepVelocity = false;
	
	public EntityHandler(GameObject g, Transform t) {
		super(g, t);
	}
	
	
	public void update() {
		if(transform.position.y > maxPosY) {
			if(!isKeepVelocity) {
				if(gameObject.getRigidbody() != null)
					gameObject.getRigidbody().linearVelocity = FlatVector.Zero();
			}
			transform.position.y = spawnY;
		} else if(transform.position.y < minPosY) {
			transform.position.y = minPosY;
		}
		
		if(transform.position.x > maxPosX) {
			if(!isKeepVelocity) {
				if(gameObject.getRigidbody() != null)
					gameObject.getRigidbody().linearVelocity = FlatVector.Zero();
			}
			transform.position.x = minSpawnX;
		} else if(transform.position.x < minPosX) {
			if(!isKeepVelocity) {
				if(gameObject.getRigidbody() != null)
					gameObject.getRigidbody().linearVelocity = FlatVector.Zero();
			}
			transform.position.x = manSpawnX;
		}
	}
}
