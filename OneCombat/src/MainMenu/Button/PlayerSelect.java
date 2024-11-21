package MainMenu.Button;

import java.awt.image.BufferedImage;
import java.io.IOException;

import src.manager.GameManager;
import system.AnimationManager;
import system.GameSystem;
import system.enumeration.SortingLayer;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class PlayerSelect extends GameObject {

	public String currentSelect = "Luffy";
	public String[] choices = { "Luffy", "Zoro", "Ace", "Sanji", "Nami", "Robin", "Vivi", "Hancock", "Usopp", "Shanks" };

	public String selectPath = "ressources/UI/Select/CurrentSelect/";

	private float scale = 1f;
	private float anchorX = 0.3f;
	private float anchorY = 0.25f;
	private float deltaAnchorX = 0.035f;
	private float deltaAnchorY = 0.1f;
	private float spaceAnchorX = 0.4f;
	private float deltaRowAnchorY = 0.06f;

	private int columns = 6;

	int id;

	public PlayerSelect(int id) {
		super();
		this.id = id;
		this.sortingLayer = SortingLayer.UI;
		transform.setScale(scale);
		renderer = new ImageUI(this, transform);
		anchorX += id * spaceAnchorX;
		((ImageUI) renderer).setAnchorX(anchorX);
		((ImageUI) renderer).setAnchorY(anchorY);
		setSelect(currentSelect);

		for (int i = 0; i < choices.length; i++) {
				float ax = anchorX - (deltaAnchorX * columns / 2) + (i%6) * deltaAnchorX + deltaAnchorX / 2f;
				float ay = anchorY + deltaAnchorY;
				GameSystem.system.sceneManager.getCurrentScene()
						.addGameObject(new SelectButtonObject(this, ax, ay, choices[i]));
			if((i+1)%6 == 0)
				anchorY += deltaRowAnchorY;
		}
	}

	// Dont need To load before, its juste for the selection(dont need performance)
	public void setSelect(String currentSelect) {
		this.currentSelect = currentSelect;
		GameManager.realPlayers[id] = currentSelect;
		try {
			BufferedImage sprite = AnimationManager.loadSprites(selectPath + currentSelect + ".png", 1, 1)[0];
			((ImageUI) renderer).setSprite(sprite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
