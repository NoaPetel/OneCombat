package src.items.type;

import Character.Components.CharacterMovement;
import Interfaces.IItem;
import src.items.Item;
import system.enumeration.ItemType;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class Jump implements IItem{
	
	private ItemType type = ItemType.Jump;
	private int stat = 1;
	
	public void set(GameObject player) {
		// Il faut set la valeur du verticalMomentum normalement
		CharacterMovement movement = player.getComponent(CharacterMovement.class);
		movement.jumpPowerUp(stat);
	}
	
	public float getStat() {
		return stat;
	}
	
	public String getPath() {
		return "ressources/items/jump.png";
	}
}
