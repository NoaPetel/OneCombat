package system.object.component.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import system.CameraManager;
import system.GameSystem;
import system.object.component.Component;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class ImageUI extends Component implements IRenderer {
	
	protected BufferedImage image;
	protected boolean isFlip = false;
	protected int pivotX;
	protected int pivotY;
	protected float anchorX;
	protected float anchorY;
	protected boolean isAnchorX = false;
	protected boolean isAnchorY = false;
	
	public ImageUI(GameObject g, Transform t) {
		super(g, t);
		pivotX = 0;
		pivotY = 0;
	}
	
	public ImageUI(GameObject g, Transform t, String path) {
		super(g, t);
		try {
			this.image = SpriteRenderer.getSprite(path);
			pivotX = 0;
			pivotY = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void setColor(Color color) {
		//color = color;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.image = sprite;
	}
	
	public void setIsFlip(boolean isFlip) {
		this.isFlip = isFlip;
	}
	
	public void setPivotPoint(int x, int y) {
		pivotX = x;
		pivotY = y;
	}
	
	public void setAnchorX(float x) {
		anchorX = x;
		isAnchorX = true;
	}
	
	public void setAnchorY(float y) {
		anchorY = y;
		isAnchorY = true;
	}
	
	public float getWidth() {
		return image.getWidth() * transform.scale;
	}
	
	public float getHeight() {
		return image.getHeight() * transform.scale;
	}
	
	public void render(Graphics g) {
		
		if(image == null) return;
		
		float width = (transform.scale * image.getWidth());
		float height = (transform.scale * image.getHeight());
		
		float newPivotX = pivotX * transform.scale;
		float newPivotY = pivotY * transform.scale;
		
		if(isAnchorX) {
			transform.position.x = GameSystem.windowWidth * anchorX - newPivotX - width/2;
		}
		if(isAnchorY) {
			transform.position.y = GameSystem.windowHeight * anchorY - newPivotY - height/2;
		}
		
		float left = transform.position.x;
		float top = transform.position.y;
		
		int flip = isFlip ? -1 : 1;
		g.drawImage(image, (int)(left - (newPivotX*flip)), (int)(top - (newPivotY)), (int)(width * flip), (int)height, null);
	}
}
