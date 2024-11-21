package items;

import Interfaces.IItem;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public abstract class ItemHealth extends MonoBehavior implements IItem{
	public ItemHealth(GameObject g, Transform t) {
		super(g, t);
	}

	public void set(GameObject gameobject) {
		
		this.gameObject = gameobject;
	}

}
