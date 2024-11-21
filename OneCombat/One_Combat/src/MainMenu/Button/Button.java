package MainMenu.Button;

import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.IOException;

import system.AnimationManager;
import system.GameSystem;
import system.InputManager;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.ImageUI;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public abstract class Button extends MonoBehavior {

	protected String spritePath;
	protected String spriteHoverPath;
	private BufferedImage sprite;
	private BufferedImage spriteHover;
	private ImageUI image;

	private float width;
	private float height;

	private boolean isHover = false;

	public Button(GameObject g, Transform t) {
		super(g, t);
		
	}
	
	public void loadSprites() {
		try {
			sprite = AnimationManager.loadSprites(spritePath, 1, 1)[0];
			spriteHover = AnimationManager.loadSprites(spriteHoverPath, 1, 1)[0];
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		image = (ImageUI) gameObject.getRenderer();
		image.setSprite(sprite);
		width = image.getWidth();
		height = image.getHeight();
	}

	public void update() {
		if (InputManager.mouseX < transform.position.x + width && InputManager.mouseX > transform.position.x
				&& InputManager.mouseY > transform.position.y && InputManager.mouseY < transform.position.y + height) {
			if (!isHover)
				onMouseEntered();
			if(InputManager.isMousePressed)
				onClick();
		} else if (isHover) {
			onMouseExited();
		}
	}

	public void onMouseEntered() {
		isHover = true;
		image.setSprite(spriteHover);
		GameSystem.system.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void onMouseExited() {
		isHover = false;
		image.setSprite(sprite);
		GameSystem.system.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	public abstract void onClick();

}
