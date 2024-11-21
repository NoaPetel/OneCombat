package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import Character.Components.CharacterMovement;
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

public class PlatformSpeed extends MonoBehavior implements IPlatformPower {
	private float dashAreaHeight = 2f;
	private float dashAreaWidth;
	private float speedup = 1.5f;
	private float timer = 0f;
	private float time = 0.1f;

	public PlatformSpeed(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
	}

	public void start() {
		this.dashAreaWidth = gameObject.getRigidbody().width;
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
				this.dashAreaWidth, this.dashAreaHeight, Layer.Character, transform.rotation);
		if (!listOfTriggeredEntities.isEmpty()) {
			Iterator<Rigidbody> entitiesToDamageIterator = listOfTriggeredEntities.iterator();
			while (entitiesToDamageIterator.hasNext()) {
				Rigidbody currentEntityRigidBody = entitiesToDamageIterator.next();
				if (currentEntityRigidBody == this.gameObject.getRigidbody()) {
					continue;
				}
				CharacterMovement c = currentEntityRigidBody.gameObject.getComponent(CharacterMovement.class);
				if(c != null) {
					c.speedPowerUp(speedup);
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
		drawRectGizmos(g, position, this.dashAreaWidth, this.dashAreaHeight, Color.red, transform.rotation);
	}

}
