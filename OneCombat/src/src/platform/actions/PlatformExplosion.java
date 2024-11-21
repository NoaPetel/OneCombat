package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.IDamagable;
import Interfaces.IPlatformPower;
import system.Physics;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PlatformExplosion extends MonoBehavior implements IPlatformPower {
	private float triggeringAreaHeight = 2f;
	private float triggeringAreaWidth;
	
	private float explosionRadius;
	private float explosionDamage = 500f;

	public PlatformExplosion(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
		
	}

	@Override
	public void start() {
		this.triggeringAreaWidth = gameObject.getRigidbody().width;
		this.explosionRadius = this.gameObject.getRigidbody().height + this.gameObject.getRigidbody().width;
	}

	@Override
	public void update() {
		this.power();
	}

	@Override
	public void power() {
		FlatVector currentGameObjectPosition = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		ArrayList<Rigidbody> listOfTriggeredEntities = Physics.OverlapPolygon(currentGameObjectPosition, this.triggeringAreaWidth, this.triggeringAreaHeight, Layer.Character, transform.rotation);
		if (!listOfTriggeredEntities.isEmpty()) {
			ArrayList<Rigidbody> listOfDamagableEntities = Physics.OverlapPolygon(this.transform.position, this.explosionRadius, this.explosionRadius, Layer.Character, transform.rotation);
			Iterator<Rigidbody> damagableEntities = listOfDamagableEntities.iterator();
			while(damagableEntities.hasNext()) {
				Rigidbody currentEntityToDamage = damagableEntities.next();
				IDamagable entityToDamage = currentEntityToDamage.gameObject.getComponent(IDamagable.class);
				if(entityToDamage != null) {
					entityToDamage.setDamage(explosionDamage);
				}
			}
			this.gameObject.destroy();
		}
	}

	
	/*
	 * For Debug
	 * 
	 */
	public void onDrawGizmos(Graphics g) {
		//Triggering Area
		FlatVector position = FlatVector.minus(this.transform.position, new FlatVector(0, 1f));
		drawRectGizmos(g, position, this.triggeringAreaWidth, this.triggeringAreaHeight, Color.red, transform.rotation);
		
		//Explosion damage Area
		drawRectGizmos(g, this.transform.position, this.explosionRadius, this.explosionRadius, Color.red, transform.rotation);
	}

}
