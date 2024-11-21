package Character.SpecialAttack.SpecialAttackObject.LuffyHand;

import java.awt.Graphics;
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

public class LuffyHandProjectil extends MonoBehavior {
	
	// Properties
	private float lifeTime = 0.25f;
	private float speed = 25;
	private float dir = 1;
	private float damage = 20;
	private FlatVector range = new FlatVector(1f, 1f);
	
	//Components
	private Rigidbody characterRB;
	

	public LuffyHandProjectil(GameObject g, Transform t, boolean dir, Rigidbody rb) {
		super(g, t);
		this.dir = dir ? 1 : -1;
		this.characterRB = rb;
	}
	
	public void start() {
		gameObject.getAnimator().playAnimation("Idle");
	}
	
	public void update() {
		//Check life time
		lifeTime -= Time.deltaTime;
		if(lifeTime <= 0f) death();
		
		//Update Movement
		move();
		
		//Check Collision forDamage
		checkCollision();
	}

	private void move()
	{
		transform.position.x += speed * Time.deltaTime * dir;
	}
	
	private void checkCollision() {
		boolean asDamaged = false;
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(transform.position, range.x, range.y, Layer.getDamagableLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				
				if(rb == characterRB) continue;
				
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					if(damagable.canTakeDamage()) {
						damagable.setDamage(damage);
						asDamaged = true;
					}
				}		
			}
			if(asDamaged) death();
		}
	}
	
	private void death() {
		gameObject.destroy();
	}
	
	public void onDrawGizmos(Graphics g) {
		drawRectGizmos(g , transform.position, range.x, range.y);
	}
}
