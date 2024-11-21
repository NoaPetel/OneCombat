package src.Ronces;

import java.io.IOException;

import src.Ronces.actions.RoncesAttack;
import src.bots.actions.BotLocomotion;
import system.enumeration.Layer;
import system.object.component.animation.Animator;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;

public class Ronces extends GameObject{
	
	private float scale = 3f;
	private float width = 0.1f;
	private float height = 0.1f;
	
	public Ronces(float x, float y) throws IOException {
		super();
		this.width*=scale;
		this.height*=scale;
		this.transform.setScale(scale);
		
		this.transform.setPositionX(x);
		this.transform.setPositionY(y);
		
		this.renderer = new SpriteRenderer(this, this.transform);
		this.animator = new Animator(this, this.transform, "Ronces", (SpriteRenderer) this.renderer);
		this.rb = Rigidbody.CreateBoxBody(2f, width, height, 1, false, 0.25f, Layer.WeakBot, this, this.transform);//0.5 pour collision
		this.components.add(new BotLocomotion(this, transform));
		this.components.add(new RoncesAttack(this, this.transform));
	}
}
