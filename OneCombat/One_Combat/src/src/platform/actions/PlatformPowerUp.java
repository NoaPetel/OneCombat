package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.IEnergy;
import Interfaces.IPlatformPower;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PlatformPowerUp extends MonoBehavior implements IPlatformPower{
	private float PowerDownAreaHeight = 2f;
	private float PowerDownAreaWidth;
	private float PowerDownPerSeconds = 2;
	private float timer = 0f;
	private float time = 0.1f;

	public PlatformPowerUp(GameObject gameObject, Transform transform) {
			super(gameObject, transform);
		}
	
	public void start() {
		this.PowerDownAreaWidth= gameObject.getRigidbody().width;
		this.PowerDownAreaHeight= gameObject.getRigidbody().height;
	}
	
	public void update() {
		this.timer += Time.deltaTime;
		if(this.timer > this.time) {
			this.power();
			this.timer = 0;
		}
	}
	
	public void power() {
		FlatVector currentGameObjectPosition = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		ArrayList<Rigidbody> listOfTriggeredEntities = Physics.OverlapPolygon(currentGameObjectPosition,
				this.PowerDownAreaWidth, this.PowerDownAreaHeight, Layer.Character, transform.rotation);
		if (!listOfTriggeredEntities.isEmpty()) {
			Iterator<Rigidbody> entitiesToDamageIterator = listOfTriggeredEntities.iterator();
			while (entitiesToDamageIterator.hasNext()) {
				Rigidbody currentEntityRigidBody = entitiesToDamageIterator.next();
				if (currentEntityRigidBody == this.gameObject.getRigidbody()) {
					continue; // Pour pas se faire des degâts a soi-même
				}
				IEnergy tiringEntity = currentEntityRigidBody.gameObject.getComponent(IEnergy.class);
				if (tiringEntity != null) {
					tiringEntity.addEnergy(PowerDownPerSeconds);
				}
			}
		}
	}

	/* For Debug
	 * 
	 */
	public void onDrawGizmos(Graphics g) {
		FlatVector position = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		drawRectGizmos(g, position, this.PowerDownAreaWidth, this.PowerDownAreaHeight, Color.red, transform.rotation);
	}

}
