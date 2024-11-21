package src.items.components;

import java.awt.Graphics;
import java.util.ArrayList;

import Interfaces.IItem;
import src.items.type.Energy;
import src.items.type.Health;
import src.items.type.Jump;
import src.items.type.Power;
import src.items.type.Speed;
import src.manager.GameManager;
import src.manager.ItemManager;
import system.Physics;
import system.Time;
import system.enumeration.ItemType;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ItemBehavior extends MonoBehavior {

	private IItem item;
	private boolean fixedItem;

	private float radius;
	private float disappearanceCooldown = 10f;

	public ItemBehavior(GameObject g, Transform t, IItem item, boolean fixed, float radius) {
		super(g, t);
		this.item = item;
		fixedItem = fixed;
		this.radius = radius;
	}

	public void start() {

	}

	public void update() {

		// Check if a player is taking the bonus
		ArrayList<Rigidbody> players = Physics.OverlapCircle(transform.position, radius, Layer.Character);
		
		if (!(players.isEmpty())) {
			ArrayList<GameObject> playerAlive = GameManager.getPlayersAlive();
			for (Rigidbody player : players) {
				
				if (playerAlive.contains(player.gameObject)) {
					item.set(player.gameObject);
					if (fixedItem) {
						ItemManager.instance.respawn(this.gameObject);
						this.gameObject.destroy();

					}
					disappearanceCooldown = 0;
				}
				
			}
		}

		if (disappearanceCooldown > 0) {
			disappearanceCooldown -= Time.deltaTime;
		} else {

			if (!fixedItem)
				this.gameObject.destroy();

		}

	}

	public void onDrawGizmos(Graphics g) {
		drawCircleGizmos(g, transform.position, radius);
	}

}
