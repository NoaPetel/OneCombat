package src.platform.actions;

import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.IDamagable;
import Interfaces.IPlatformPower;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PlatformHeal extends MonoBehavior implements IPlatformPower{
	
	private float healAreaHeight = 2f;
	private float healAreaWidth;
	private float healPerSeconds = 2;
	private float timer = 0f;
	private float time = 0.1f;
	
	public PlatformHeal(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
	}
	
	@Override
	public void start() {
		this.healAreaWidth = gameObject.getRigidbody().width;
	}
	
	@Override
	public void update() {
		this.timer += Time.deltaTime;
		if(this.timer > this.time) {
			this.power();
			this.timer = 0;
		}
	}
	
	@Override
	public void power() {
		FlatVector currentGameObjectPosition = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		ArrayList<Rigidbody> listOfTriggeredEntities = Physics.OverlapPolygon(currentGameObjectPosition, this.healAreaWidth, this.healAreaHeight, Layer.Character, transform.rotation);
		if(!listOfTriggeredEntities.isEmpty()) {
			Iterator<Rigidbody> entitiesToHealIterator = listOfTriggeredEntities.iterator();
			while(entitiesToHealIterator.hasNext()) {
				Rigidbody currentEntityRigidBody = entitiesToHealIterator.next();
				IDamagable damagableEntity = currentEntityRigidBody.gameObject.getComponent(IDamagable.class);
				if(damagableEntity != null) {
					damagableEntity.addHealth(healPerSeconds);
				}
			}
		}
	}
}
