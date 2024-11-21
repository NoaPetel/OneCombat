package MainMenu.Button;

import system.enumeration.SortingLayer;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class QuitButtonObject extends GameObject {
	
	public float width;
	public float height;
	public float scale = 1.5f;

	public QuitButtonObject() {
		super();
		sortingLayer = SortingLayer.UI;
		transform.setScale(scale);
		renderer = new ImageUI(this, transform);
		components.add(new QuitButton(this, transform));
		((ImageUI)renderer).setAnchorX(0.5f);
		((ImageUI)renderer).setAnchorY(0.75f);
	}
}
