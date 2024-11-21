package system.object.component.monobehavior;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import system.GameSystem;
import system.object.component.Component;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class MonoBehavior extends Component {

	public MonoBehavior(GameObject g, Transform t) {
		super(g, t);
	}

	protected boolean isEnabled = true;

	public void start() {

	}

	public void update() {
		
	}

	public void onDrawGizmos(Graphics g) {
		
	}
	
	public static void drawTextGizmos(Graphics g, char[] text, FlatVector position, Color color) {
		g.setColor(color);
		
		float posX = position.x;
		float posY = position.y;

		//if (!GameSystem.system.cameraManager.inView(posX, posY, 1f, 1f))
			//return;
		
		float[] pos = GameSystem.system.cameraManager.getPosInView(posX, posY);
		
		g.drawChars(text, 0, text.length, (int)pos[0], (int)pos[1]);
	}

	public static void drawRectGizmos(Graphics g, FlatVector position, float w, float h) {
		drawRectGizmos(g, position, w, h, Color.red);
	}
	
	public static void drawRectGizmos(Graphics g, FlatVector position, float w, float h, Color color) {
		
		g.setColor(color);

		float scale = GameSystem.system.cameraManager.getScale();
		float newWidth = (w) / scale;
		float newHeight = (h) / scale;
		float posX = position.x;
		float posY = position.y;

		float left = posX - newWidth / 2f;
		float top = posY - newHeight / 2f;

		if (!GameSystem.system.cameraManager.inView(left, top, newWidth, newHeight))
			return;

		float[] pos = GameSystem.system.cameraManager.getPosInView(posX, posY);

		g.drawRect((int) (pos[0] - newWidth / 2), (int) (pos[1] - newHeight / 2), (int) newWidth, (int) newHeight);
	}
	
	public static void drawRectGizmos(Graphics g, FlatVector position, float w, float h, Color color, float rotation) {
		
		g.setColor(color);

		float scale = GameSystem.system.cameraManager.getScale();
		float newWidth = (w) / scale;
		float newHeight = (h) / scale;
		float posX = position.x;
		float posY = position.y;

		float left = posX - newWidth / 2f;
		float top = posY - newHeight / 2f;

		if (!GameSystem.system.cameraManager.inView(left, top, newWidth, newHeight))
			return;

		float[] pos = GameSystem.system.cameraManager.getPosInView(posX, posY);

		Rectangle2D rect = new Rectangle2D.Double(0, 0, newWidth, newHeight);

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(pos[0] - newWidth / 2, pos[1] - newHeight / 2);
		affineTransform.rotate(rotation, newWidth / 2f, newHeight / 2f);
		// it's been while, you might have to perform the rotation and translate in the
		// opposite order

		Shape rotatedRect = affineTransform.createTransformedShape(rect);

		Graphics2D graphics = (Graphics2D) g; // get it from whatever you're drawing to

		graphics.draw(rotatedRect);
	}
	
	
	
	public static void drawRectRelativeGizmos(Graphics g, FlatVector position, float w, float h, Color color, FlatVector currentPosition, boolean isFacingRight) {
		FlatVector endPosition = getRelativePosition(currentPosition, position, isFacingRight);
		drawRectGizmos(g, endPosition, w, h, color);
	}

	public static void drawCircleGizmos(Graphics g, FlatVector position, float r) {
		drawCircleGizmos(g, position, r, Color.red);
	}
	
	public static void drawCircleGizmos(Graphics g, FlatVector position, float r, Color color) {

		g.setColor(color);

		float scale = GameSystem.system.cameraManager.getScale();
		float newRadius = (r) / scale;
		
		float posX = position.x;
		float posY = position.y;

		float left = posX - newRadius;
		float top = posY - newRadius;

		if (!GameSystem.system.cameraManager.inView(left, top, newRadius*2f, newRadius*2f))
			return;
		
		float[] pos = GameSystem.system.cameraManager.getPosInView(posX, posY);
		
		g.drawOval((int) (pos[0] - newRadius), (int) (pos[1] - newRadius), (int)newRadius*2, (int)newRadius*2);
	}

	public static FlatVector getRelativePosition(FlatVector currentPosition, FlatVector newPosition, boolean isFacingRight) {
		FlatVector endPosition = new FlatVector(newPosition.x, newPosition.y);
		if (!isFacingRight)
			endPosition.x = -endPosition.x;
		return FlatVector.plus(currentPosition, endPosition);
	}
}
