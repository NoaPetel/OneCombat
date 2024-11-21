package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
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

public class PlatformDamage extends MonoBehavior implements IPlatformPower{

	// private FlatVector healRange = new FlatVector(3f, 3f);

	private float damageAreaHeight = 2f;
	private float damageAreaWidth;
	private float damagePerSeconds = 5f;
	private float timer = 0f;
	private float time = 2f;

	public PlatformDamage(GameObject gameObject, Transform transform) {
			super(gameObject, transform);
		}
	
	public void start() {
		this.damageAreaWidth = gameObject.getRigidbody().width;
	}
	
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
		ArrayList<Rigidbody> listOfTriggeredEntities = Physics.OverlapPolygon(currentGameObjectPosition,
				this.damageAreaWidth, this.damageAreaHeight, Layer.Character, transform.rotation);
		if (!listOfTriggeredEntities.isEmpty()) {
			Iterator<Rigidbody> entitiesToDamageIterator = listOfTriggeredEntities.iterator();
			while (entitiesToDamageIterator.hasNext()) {
				Rigidbody currentEntityRigidBody = entitiesToDamageIterator.next();
				if (currentEntityRigidBody == this.gameObject.getRigidbody()) {
					continue; // Pour pas se faire des degâts a soi-même
				}
				IDamagable damagableEntity = currentEntityRigidBody.gameObject.getComponent(IDamagable.class);
				if (damagableEntity != null) {
					damagableEntity.setDamage(damagePerSeconds);
				}
			}
		}
	}
	
	/*
	 * For Debug
	 * 
	 */
	public void onDrawGizmos(Graphics g) {
		FlatVector position = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		drawRectGizmos(g, position, this.damageAreaWidth, this.damageAreaHeight, Color.red, transform.rotation);
	}
}
