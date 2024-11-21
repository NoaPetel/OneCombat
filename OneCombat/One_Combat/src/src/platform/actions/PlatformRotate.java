package src.platform.actions;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class PlatformRotate extends MonoBehavior{
	public PlatformRotate(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
	}
	
	@Override
	public void start() {
		Rigidbody gameObjectRigidBody = this.gameObject.getRigidbody();
		gameObjectRigidBody.rotate((float)Math.PI / 8f);
		gameObjectRigidBody.addRotationalForce(1f);
	}
}
