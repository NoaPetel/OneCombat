package Test;

import java.awt.Color;

import system.enumeration.Layer;
import system.object.component.renderer.RectRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class BasicSquareObject extends GameObject{
	
	float width = 0.1f;
	float height = 0.1f;
	
	public BasicSquareObject(FlatVector position) {
		super();
		transform.setPosition(position);
		transform.setRotation((float)(Math.random() * 2 * Math.PI));
		renderer = new RectRenderer(this, transform, Color.black, width, height);
		rb = Rigidbody.CreateBoxBody(1f, width, height, 0.2f, false, 0.8f, Layer.Default, this, transform);
		rb.addForce(new FlatVector((float) Math.random() * 20f - 10, (float) Math.random() * 20f * 10));
	}
}
