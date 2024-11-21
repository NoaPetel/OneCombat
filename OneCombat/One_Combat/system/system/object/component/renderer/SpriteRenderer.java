package system.object.component.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import system.CameraManager;
import system.GameSystem;
import system.object.component.Component;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class SpriteRenderer extends Component implements IRenderer {

	protected BufferedImage sprite;
	protected boolean isFlip = false;
	protected int pivotX;
	protected int pivotY;
	
	public SpriteRenderer(GameObject g, Transform t) {
		super(g, t);
	}
	
	public SpriteRenderer(GameObject g, Transform t, BufferedImage sprite) {
		super(g, t);
		this.sprite = sprite;
		pivotX = sprite.getWidth() / 2;
		pivotY = sprite.getHeight() / 2;
	}
	
	public SpriteRenderer(GameObject g, Transform t, String path) {
		super(g, t);
		try {
			this.sprite = getSprite(path);
			pivotX = sprite.getWidth() / 2;
			pivotY = sprite.getHeight() / 2;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setColor(Color color) {
		//color = color;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public void setIsFlip(boolean isFlip) {
		this.isFlip = isFlip;
	}
	
	public void setPivotPoint(int x, int y) {
		pivotX = x;
		pivotY = y;
	}
	
	public void render(Graphics g) {
		
		if(sprite == null) return;
		
		float scale = GameSystem.system.cameraManager.getScale();
		float width = (transform.scale * sprite.getWidth()) / scale * CameraManager.PIXELSIZE;
		float height = (transform.scale * sprite.getHeight()) / scale * CameraManager.PIXELSIZE;
		
		float newPivotX = pivotX * transform.scale / scale * CameraManager.PIXELSIZE;
		float newPivotY = pivotY * transform.scale / scale * CameraManager.PIXELSIZE;
		
		float left = transform.position.x-width / 2f;
		float top = transform.position.y-height / 2f;
		
		if(!GameSystem.system.cameraManager.inView(left, top, width, height)) return;
		
		float [] pos = GameSystem.system.cameraManager.getPosInView(transform.position.x, transform.position.y);
		
		int flip = isFlip ? -1 : 1;
		g.drawImage(sprite, (int)(pos[0]- (newPivotX*flip)), (int)(pos[1]- (newPivotY)), (int)(width * flip), (int)height, null);
	}
	
	public static BufferedImage getSprite(String path) throws IOException {
		BufferedImage img = ImageIO.read(new File(path));
		return img;
	}
	
}
