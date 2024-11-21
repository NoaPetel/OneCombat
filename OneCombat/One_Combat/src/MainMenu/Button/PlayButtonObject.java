package MainMenu.Button;

import system.enumeration.SortingLayer;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class PlayButtonObject extends GameObject {

	public float width;
	public float height;
	public float scale = 1.5f;

	public PlayButtonObject() {
		super();
		sortingLayer = SortingLayer.UI;
		transform.setScale(scale);
		renderer = new ImageUI(this, transform);
		components.add(new PlayButton(this, transform));
		((ImageUI)renderer).setAnchorX(0.5f);
		((ImageUI)renderer).setAnchorY(0.6f);
	}

}
