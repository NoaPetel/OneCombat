package src.bots.actions;

import java.util.ArrayList;

import Interfaces.IDamagable;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class BotHit extends MonoBehavior {

	private float damage = 10;
	private float width;
	private float height;
	
	private float hitTime = 1.2f;
	private float onHitTime = 0f;
	
	BotHealth BotHealth;

	public BotHit(GameObject g, Transform t, float width, float height) {
		super(g, t);
		this.width = width;
		this.height = height;
	}
	
	public void start() {
		BotHealth = gameObject.getComponent(BotHealth.class);
		onHitTime = hitTime;
	}
	
	public void update() {
		onHitTime -= Time.deltaTime;
		//hit();
	}

	public void hit() {
		
		if(!canHit()) return; 

		onHitTime = hitTime;
		
		FlatVector position = transform.position;
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, this.width, this.height, Layer.Character);
		if (rbs.size() > 0) {
			for (Rigidbody rb : rbs) {
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if (damagable != null) {
					damagable.setDamage(damage);
				}

			}
		}
	}
	
	public boolean canHit() {
		return BotHealth.canBeMovable() && onHitTime <= 0;
	}

}
