package src.bots.Missile;

import java.io.IOException;

import Character.Components.CharacterLocomotion;
import src.bots.actions.*;
import system.enumeration.Layer;
import system.object.component.animation.Animator;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;
import system.object.component.transform.*;

public class Missile extends GameObject{
	
	private float SCALE = 2.5f;
	
	public Missile(Transform transform, boolean flip) throws IOException {
		super();
		
		int orientation = 1;
		if(flip)
		{
			orientation = -1;
		}
		this.transform.setPositionX(transform.getPositionX()+orientation);
		this.transform.setPositionY(transform.getPositionY());
		this.transform.setScale(SCALE);
		this.renderer = new SpriteRenderer(this, this.transform);
		this.animator = new Animator(this, this.transform, "Missile", (SpriteRenderer) this.renderer);
		this.rb = Rigidbody.CreateBoxBody(0.1f, 0.23f, 0.23f, 0.01f, false, 0.01f, Layer.Projectil, this, this.transform);
		
		this.components.add(new BotLocomotion(this, this.transform));
		this.components.add(new MissileMove(this, this.transform, orientation));
	}
}
