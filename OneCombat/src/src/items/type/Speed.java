package src.items.type;

import Character.Components.CharacterMovement;
import Interfaces.IItem;
import system.Time;
import system.enumeration.ItemType;
import system.object.gameobject.GameObject;

public class Speed implements IItem{
	
	private ItemType type = ItemType.Speed;
	private float stat = 1f;
	
	public void set(GameObject player) {
		// il faut set la valeur du movement (momentum ou moveX je ne sais plus)
		CharacterMovement movement = player.getComponent(CharacterMovement.class);
		movement.speedPowerUp(stat);
	}
	
	public float getStat() {
		return stat;
	}
	
	public String getPath() {
		return "ressources/items/speed.png";
	}
}
