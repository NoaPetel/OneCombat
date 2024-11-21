package src.items.type;

import Character.Components.CharacterHealth;
import Interfaces.IItem;
import system.enumeration.ItemType;
import system.object.gameobject.GameObject;

public class Health implements IItem{
	
	private ItemType type = ItemType.Health;
	private float stat = 0f;
	
	public void set(GameObject player) {
		CharacterHealth characterHealth = player.getComponent(CharacterHealth.class);
		characterHealth.addHealth(stat);
	}
	
	public float getStat() {
		return stat;
	}
	
	public String getPath() {
		return "ressources/items/health.png";
	}
}
