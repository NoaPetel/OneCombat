package src.map.Tile;

import java.awt.image.BufferedImage;

import Character.Components.CharacterLocomotion;
import Interfaces.IDamagable;
import src.manager.TileManager;
import src.map.Tile.Components.TileHealth;
import system.enumeration.Layer;
import system.enumeration.TileType;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;


public class Tile extends GameObject{
	
	public static float LENGTH_TILE = 1f;
	public static float HEIGHT_TILE = 1f;
	
	public static float SCALE = 1f/0.31f;
	
	float posx;
	float posy;
	BufferedImage image;
	boolean canTakeDamage = true;
	
	public Tile(int type, float posx, float posy, BufferedImage[] sprites) {
		super();
		transform.setScale(SCALE);
		int num = 0;
		this.posx = posx;
		this.posy = posy;
	    transform.setPositionX(posx);
	    transform.setPositionY(posy);
	    float health = 0;
	    boolean takedamage = true;
		if(type == TileType.Grass.getValue()) {
			num = 1;
			health = TileType.getHealth(TileType.Grass.getValue());
		}
		else if(type == TileType.Dirt.getValue()) {
			num = 5;
			health = TileType.getHealth(TileType.Dirt.getValue());
		}
		else if(type == TileType.Rock.getValue()) {
			num = 37;
			health = TileType.getHealth(TileType.Rock.getValue());
		}
		else if(type == TileType.Wall.getValue()) { // used ?? 
			num =  38;
			health = TileType.getHealth(TileType.Wall.getValue());
			takedamage = false;
		}

		renderer = new SpriteRenderer(this, transform, sprites[num]);

	    rb = Rigidbody.CreateBoxBody(1f, LENGTH_TILE, HEIGHT_TILE, 1f, true, 0f, Layer.Map, this, transform);
	    components.add(new TileHealth(this, transform, health, takedamage));

	}
	
	
	public float getPosx() {
		return this.posx;
	}
	
	public float getPosy() {
		return this.posy;
	}
	
}
