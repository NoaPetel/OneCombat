package src.items.type;

import Character.Components.CharacterAttack;
import Interfaces.IItem;
import system.enumeration.ItemType;
import system.object.gameobject.GameObject;

public class Power implements IItem{
	
	private ItemType type = ItemType.Power;
	private float stat = 0f;
	
	public void set(GameObject player) {
		CharacterAttack attack = player.getComponent(CharacterAttack.class);
		attack.powerPowerUp(stat);
	}
	
	public float getStat() {
		return stat;
	}
	
	public String getPath() {
		return "ressources/items/power.png";
	}
}