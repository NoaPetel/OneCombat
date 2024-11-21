package src.map.Tile.Components;

import java.awt.Color;
import java.awt.Graphics;

import Interfaces.IDamagable;
import src.manager.TileManager;
import src.map.Tile.Tile;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class TileHealth extends MonoBehavior implements IDamagable {
	
	float health;
	boolean canTakeDamage = true;

	public TileHealth(GameObject g, Transform transform, float health, boolean takeD) {
		super(g,transform);
		this.health = health;
		this.canTakeDamage = takeD;
	}
	
	public TileHealth(GameObject g, Transform transform, float health) {
		super(g,transform);
		this.health = health;
	}

	public float getHealth() {
		return this.health;
	}

	@Override
	public void setDamage(float amount) {
		if(this.canTakeDamage) {
			health -= amount;
			if (health <= 0) {
				death();
				//return; // destructon de la tile
			}
		}
	}

	public void death() {
		Tile gTile = (Tile) gameObject;
		TileManager.instance.updateTileType(gTile);
		//GameSystem.system.sceneManager.getCurrentScene().addGameObject(new ExplosionObject(transform.position));
		this.destroy();
	}

	@Override
	public void addHealth(float amount) {
		return;
		
	}

	@Override
	public boolean canTakeDamage() {
		return this.canTakeDamage;
	}
	
	@Override
	public void onDrawGizmos(Graphics g) {
		drawTextGizmos(g, Float.toString(health).toCharArray(), FlatVector.minus(transform.position, new FlatVector(0.2f, 0)), Color.white);
	}

}
