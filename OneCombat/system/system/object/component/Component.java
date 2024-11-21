package system.object.component;

import system.object.IObject;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class Component implements IObject {
	
	public GameObject gameObject;
	public Transform transform;
	protected String tag;
	
	public Component(GameObject g, Transform t) {
		gameObject = g;
		transform = t;
	}
	
	public Component(GameObject g) {
		gameObject = g;
	}
	
	@Override
	public void destroy() {
		gameObject.destroy();
	}
	
	@Override
	public <T> T getComponent (Class<?> c) {
		return gameObject.getComponent(c);
	}
	
	@Override
	public <T> boolean tryGetComponent(T t) {
		return gameObject.tryGetComponent(t);
	}

}
