package src.items.type;

import Character.Components.CharacterEnergy;
import Interfaces.IItem;
import system.enumeration.ItemType;
import system.object.gameobject.GameObject;

public class Energy implements IItem {
	private ItemType type = ItemType.Energy;
	private float stat = 1f;

	public void set(GameObject player) {
		CharacterEnergy characterEnergy = player.getComponent(CharacterEnergy.class);
		characterEnergy.addEnergy(stat);
	}

	public float getStat() {
		return stat;
	}
	
	public String getPath() {
		return "ressources/items/energie.png";
	}
	
}
