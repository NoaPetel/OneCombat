package system.object.component.renderer;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import system.CameraManager;
import system.GameSystem;
import system.object.component.Component;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class RectRenderer extends Component implements IRenderer {
	
	protected Color color;
	protected float width, height;
	
	public RectRenderer(GameObject g, Transform t, Color c, float sizeX, float sizeY) {
		super(g, t);
		color = c;
		width = sizeX;
		height = sizeY;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void render(Graphics g) {
		
		float scale = GameSystem.system.cameraManager.getScale();
		float newWidth = (/*transform.scale * */width) / scale;
		float newHeight = (/*transform.scale * */height) / scale;
		
		float left = transform.position.x-newWidth / 2f;
		float top = transform.position.y-newHeight / 2f;
		
		if(!GameSystem.system.cameraManager.inView(left, top, newWidth, newHeight)) return;
		
		float [] pos = GameSystem.system.cameraManager.getPosInView(transform.position.x, transform.position.y);
		
		g.setColor(color);
		//g.fillRect((int)pos[0], (int)pos[1], (int)width, (int)height);
		
		Rectangle2D rect = new Rectangle2D.Double(0, 0, newWidth, newHeight);

		AffineTransform affineTransform = new AffineTransform();
		affineTransform.translate(pos[0] - newWidth / 2, pos[1] - newHeight / 2);
		affineTransform.rotate(transform.rotation, newWidth / 2f, newHeight / 2f);
		// it's been while, you might have to perform the rotation and translate in the
		// opposite order

		Shape rotatedRect = affineTransform.createTransformedShape(rect);

		Graphics2D graphics = (Graphics2D) g; // get it from whatever you're drawing to

		graphics.fill(rotatedRect);
	}
}
