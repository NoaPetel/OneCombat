package src.items;

import Interfaces.IItem;
import src.items.components.ItemBehavior;
import src.items.components.ItemLocomotion;
import src.items.type.Energy;
import src.items.type.Health;
import src.items.type.Jump;
import src.items.type.Power;
import src.items.type.Speed;
import system.enumeration.ItemType;
import system.object.component.renderer.SpriteRenderer;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class Item extends GameObject {

	public FlatVector position;
	
	public float radius = 0.16f;
	private float scale = 2;
	private IItem item;
	private String path;
	private ItemType type;
	
	public Item(ItemType type, boolean isFixed, FlatVector position) {
		super();
		radius *= scale;
		transform.setScale(scale);
		transform.setPosition(position);
		this.type = type;
		
		switch (this.type) {
		case Speed:
			item = new Speed();
			path = "ressources/items/speed.png";
			break;
		case Health:
			item = new Health();
			path = "ressources/items/health.png";
			break;
		case Jump:
			item = new Jump();
			path = "ressources/items/jump.png";
			break;
		case Power:
			item = new Power();
			path = "ressources/items/power.png";
			break;
		case Energy:
			item = new Energy();
			path = "ressources/items/energy.png";
			break;
		}
		renderer = new SpriteRenderer(this, transform, path);
		components.add(new ItemBehavior(this, transform, item, isFixed, radius));
		components.add(new ItemLocomotion(this, transform));
	}
	
	
	public static Item CreateItem(FlatVector position, float scale, ItemType type, boolean isFixed) {
		return new Item(type, isFixed, position);
	}
	
}
