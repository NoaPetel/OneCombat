package info3.game.gametest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import system.enumeration.ShapeType;
import system.object.component.rigidbody.Rigidbody;
import system.physics.FlatMath;
import system.physics.FlatVector;
import system.physics.FlatWorld;

public class TestPhysics {

	private FlatWorld flatWorld;
	float inputX;
	float inputY;
	ArrayList<Color> colors = new ArrayList<Color>();
	Random r;
	float timer;
	float minTimeToSpawn = 100f;
	boolean mouse = false;

	public TestPhysics() {
		flatWorld = new FlatWorld();
		r = new Random();
		colors.add(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		colors.add(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		colors.add(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		
		Rigidbody body;
		body = Rigidbody.CreateBoxBody(1f, 500, 30f, 1f, true, 0.5f, null, null, null);
		body.moveTo(new FlatVector(200, 100));
		flatWorld.addBody(body);
		body.rotateTo((float) (Math.PI/6));
		
		body = Rigidbody.CreateBoxBody(1f, 500, 50f, 1f, true, 0.5f, null, null, null);
		flatWorld.addBody(body);
		body.moveTo(new FlatVector(700, 400));
		body.rotateTo((float) (-Math.PI/6));
		
		body = Rigidbody.CreateBoxBody(1f, 800f, 50f, 1f, true, 0.5f, null, null, null);
		flatWorld.addBody(body);
		body.moveTo(new FlatVector(500, 700));
		timer = minTimeToSpawn;
	}

	void tick(long elapsed) {
//		float speed = 0.2f;
		float forceMagnitude = 800000f;
		//inputX = 1f;
		if(inputX != 0 || inputY != 0) {
			FlatVector forceDirection = FlatMath.normalize(new FlatVector(inputX, inputY));
			FlatVector force = FlatVector.times(forceDirection, forceMagnitude);
			flatWorld.getBody(1).addForce(force);
		}
		
		
//		for (int i = 0; i < flatWorld.BodyCount(); i++) {
//			Rigidbody body = flatWorld.getBody(i);
//			body.rotate((float)Math.PI/2 * elapsed/1000f);
//		}
		timer -= elapsed;
		
		if(mouse && timer <= 0) {
			mouse = false;
			timer = minTimeToSpawn;
			boolean type = r.nextInt(2) == 0;
			colors.add(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			float x = Game.game.m_listener.mouseX;
			float y = Game.game.m_listener.mouseY;
			if(type) {
				float radius = r.nextInt(25) + 5f;
				Rigidbody body = Rigidbody.CreateCircleBody(1f, radius, 2f, false, 0.6f, null, null, null);
				body.moveTo(new FlatVector(x, y));
				flatWorld.addBody(body);
			} else {
				float width = r.nextInt(25) + 5f;
				float height = r.nextInt(25) + 5f;
				Rigidbody body = Rigidbody.CreateBoxBody(1f, width, height, 1f, false, 0.6f, null, null, null);
				body.moveTo(new FlatVector(x, y));
				flatWorld.addBody(body);
			}
		}
		
		flatWorld.update(elapsed / 1000f, 1);
		//wrapScreen();
	}

	public void setInputX(float iX) {
		this.inputX = iX;
	}

	public void setInputY(float iY) {
		this.inputY = iY;
	}
	
	public void setMouse(boolean click) {
		this.mouse = click;
	}

	public void paint(Graphics g, int width, int height) {
		for (int i = 0; i < flatWorld.BodyCount(); i++) {
			Rigidbody body = flatWorld.getBody(i);
			g.setColor(colors.get(i));
			if (body.shapeType == ShapeType.Circle) {
				g.fillOval((int) (body.position.x - body.radius), (int) (body.position.y - body.radius),
						(int) body.radius * 2, (int) body.radius * 2);
			} else {
				Rectangle2D rect = new Rectangle2D.Double(0, 0, body.width, body.height);

				AffineTransform affineTransform = new AffineTransform();
				affineTransform.translate(body.position.x - body.width/2, body.position.y - body.height/2);
				affineTransform.rotate(body.rotation, body.width / 2, body.height / 2);

				Shape rotatedRect = affineTransform.createTransformedShape(rect);
				Graphics2D graphics = (Graphics2D) g;
				graphics.fill(rotatedRect);
				// g.fillRect((int)body.position.x, (int)body.position.y, (int)body.width,
				// (int)body.height);
			}
		}
		
//		for (int i = 0; i < flatWorld.contactPointsList.size(); i++) {
//			FlatVector contact = flatWorld.contactPointsList.get(i);
//			g.setColor(Color.orange);
//			g.fillRect((int)contact.x - 5, (int)contact.y -5, 10, 10);
//		}
	}
	
	
	void wrapScreen() {
		
		float viewWidth = 1000;
		float viewHeight = 800;
		
		for (int i = 0; i < flatWorld.BodyCount(); i++) {
		Rigidbody body = flatWorld.getBody(i);
			if(body.position.x < -10) body.move(new FlatVector(viewWidth, 0f));
			if(body.position.x > 1100) body.move(new FlatVector(-viewWidth, 0f));
			if(body.position.y < -10) body.move(new FlatVector(0f, viewHeight));
			if(body.position.y > 810) body.move(new FlatVector(0f, -viewHeight));
		}
	}
}
