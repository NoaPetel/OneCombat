package src.bots.actions;

import java.util.ArrayList;

import Interfaces.IDamagable;
import system.Constante;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;


public class MissileMove extends MonoBehavior implements IDamagable{
	

	private int orientation;
	
	private float dyingTimer = 0;
	private float timer = 0.2f;
	private boolean isDying = false;
	private float DAMAGE = 50f;
	
	BotLocomotion BotLocomotion;

	public MissileMove(GameObject g, Transform t, int orientation) {
		super(g, t);
		this.orientation = orientation;
	}

	@Override
	public void start() {
		BotLocomotion = gameObject.getComponent(BotLocomotion.class);
	}

	@Override
	public void update() {
		float movement = Time.deltaTime * 12 * orientation;

		float tmp = this.transform.getPositionX();
		this.transform.setPositionX(tmp+movement);
		
		//hit ?
		FlatVector position = transform.position;
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, 0.25f, 0.25f, Layer.getDamagableLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					if(!isDying)
					{
						isDying();
						orientation = 0;
						damagable.setDamage(DAMAGE);
						this.gameObject.getRigidbody().gravityScale = 0f;
						//this.gameObject.getRigidbody().isStatic = true;
					}
					
				}
			}
		//en dehors de la map en x ?
		if(this.transform.getPositionX() > Constante.MAP_LENGTH || this.transform.getPositionX() < -Constante.MAP_LENGTH ) {
			isDying();
		}
		//en dehors de la map en y ?
		if(this.transform.getPositionY() > Constante.MAP_HEIGHT || this.transform.getPositionY() < -Constante.MAP_HEIGHT ) {
			isDying();
		}

		}
		if(isDying) {
			this.gameObject.getRigidbody().linearVelocity = FlatVector.Zero();
			if(dyingTimer > 0)
			{
				dyingTimer -= Time.deltaTime;
			}
			else if(dyingTimer <= 0) {
				this.death();
			}
		}
	}
		
	@Override
	public void setDamage(float amount) {
		this.destroy();
		
	}
	
	
	public void isDying() {
		isDying = true;
		BotLocomotion.explode();
		dyingTimer = this.timer;
	}

	@Override
	public void death() {
		this.destroy();
	}

	@Override
	public void addHealth(float amount) {
		return;
	}

	@Override
	public boolean canTakeDamage() {
		return true;
	}
}