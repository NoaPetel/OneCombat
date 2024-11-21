package src.portal;

import java.awt.Color;
import java.io.IOException;

import Character.Components.CharacterLocomotion;
import system.enumeration.Layer;
import system.object.component.animation.Animator;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;

import system.object.gameobject.*;
import src.portal.Components.*;

import system.object.component.renderer.*;

public class Portal extends GameObject{
	
	private float scale = 2f;
	private float width = 0.44f;
	private float height = 0.88f;
	
	public Portal twin = null;
	public int direction = 0;
	
	public Portal(float x, float y, int direction) throws IOException {
		super();

		this.width*=scale;
		this.height*=scale;
		this.transform.setScale(scale);
		
		this.transform.setPositionX(x);
		this.transform.setPositionY(y);
		
		this.direction = direction;
		
		this.renderer = new SpriteRenderer(this, this.transform);
		this.animator = new Animator(this, this.transform, "Portal", (SpriteRenderer) this.renderer);
		//this.renderer = new RectRenderer(this, this.transform, Color.BLUE, width, height);
		
		
		this.components.add(new CharacterLocomotion(this, this.transform));
		
		this.components.add(new PortalTeleport(this, this.transform, this, direction));
	}

}
