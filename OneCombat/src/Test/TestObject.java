package Test;

import java.awt.Color;

import system.enumeration.Layer;
import system.object.component.renderer.RectRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class TestObject extends GameObject {

	private float width = 8f;
	private float height = 0.1f;
	
	public TestObject() {
		super();
		renderer = new RectRenderer(this, transform, Color.white, width, height);
		transform.setPosition(new FlatVector(-10f, -5f));
		rb = Rigidbody.CreateBoxBody(0f, width, height, 1f, false, 0f, Layer.Map, this, transform);
		rb.rotate((float)Math.PI / 8f);
		rb.addRotationalForce(1f);
		rb.freezePosition = true;
	}
}
