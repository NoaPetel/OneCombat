package src.platform.actions;

import Interfaces.IDamagable;
import Interfaces.IPlatformPower;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class PlatformHealth extends MonoBehavior implements IDamagable{
	private float health;
	protected PlatformSplit onDeathEvent;
	
	public PlatformHealth(GameObject gameObject, Transform transform, float health) {
		super(gameObject, transform);
		this.health = health;
	}
	
	@Override
	public void setDamage(float amount) {
		this.health -= amount;
		if(this.health <= 0) {
			this.death();
		}
		
	}
	
	@Override
	public void start() {
		this.onDeathEvent = this.gameObject.getComponent(PlatformSplit.class);
	}
	
	@Override
	public void death() {
		this.onDeathEvent.splitPlatform();
		this.destroy();
		
	}
	
	@Override
	public void addHealth(float amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canTakeDamage() {
		// TODO Auto-generated method stub
		return this.health > 0;
	}
}
