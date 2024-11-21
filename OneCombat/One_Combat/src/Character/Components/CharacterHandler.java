package Character.Components;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class CharacterHandler extends MonoBehavior {

	private float maxPosY = 25f;
	private float minPosY = -10f;
	private boolean isKeepVelocity = false;
	
	public CharacterHandler(GameObject g, Transform t) {
		super(g, t);
	}
	
	
	public void update() {
		if(transform.position.y > maxPosY) {
			if(!isKeepVelocity)
				gameObject.getRigidbody().linearVelocity = FlatVector.Zero();
			transform.position.y = minPosY;
		}
	}
}
