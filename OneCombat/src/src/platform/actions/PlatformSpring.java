package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.IPlatformPower;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PlatformSpring extends MonoBehavior implements IPlatformPower{

	SpriteRenderer spriteRenderer;
	public boolean contact = true;
	private float springAreaWidth;
	private float springAreaHeight;
	private float timer = 0f;
	private float time = 0.1f;

	public PlatformSpring(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
	}

	public void start() {
		this.springAreaWidth = gameObject.getRigidbody().width;
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
				this.springAreaWidth, this.springAreaHeight, Layer.Character, transform.rotation);
		if (!listOfTriggeredEntities.isEmpty()) {
			Iterator<Rigidbody> entitiesToSpringIterator = listOfTriggeredEntities.iterator();
			while (entitiesToSpringIterator.hasNext()) {
				Rigidbody currentRigidBody = (Rigidbody) entitiesToSpringIterator.next();
				currentRigidBody.addForce(new FlatVector(0, -1000));
			}
		}
	}

	/*
	 * For Debug
	 * 
	 */
	public void onDrawGizmos(Graphics g) {
		FlatVector position = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		drawRectGizmos(g, position, this.springAreaWidth, this.springAreaHeight, Color.red, transform.rotation);
	}
}
