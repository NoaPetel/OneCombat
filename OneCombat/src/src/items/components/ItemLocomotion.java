package src.items.components;

import system.object.component.animation.Animator;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ItemLocomotion extends MonoBehavior {

	private FlatVector upPosition;
	private FlatVector downPosition;
	private float deltaPos = 0.1f;
	private float moveSmoothness = 0.01f;
	private boolean goUp = true;

	public ItemLocomotion(GameObject g, Transform t) {
		super(g, t);
		upPosition = new FlatVector(transform.position.x, transform.position.y - deltaPos);
		downPosition = new FlatVector(transform.position.x, transform.position.y + deltaPos);
	}

	public void start() {

	}
	
	public void update() {

		if(goUp) {
			transform.position = FlatVector.lerp(transform.position, upPosition, moveSmoothness);
			if(transform.position.y <= upPosition.y + 0.05) {
				goUp = false;
			}
		} else {
			transform.position = FlatVector.lerp(transform.position, downPosition, moveSmoothness);
			if(transform.position.y >= downPosition.y - 0.05) {
				goUp = true;
			}
		}
	}

}
