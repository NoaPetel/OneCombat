package Test;

import system.enumeration.SortingLayer;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class BackgroundObject extends GameObject {
	
	public BackgroundObject() {
		super();
		this.sortingLayer = SortingLayer.Background;
		transform.setScale(3f);
		renderer = new ImageUI(this, transform, "ressources/Background/BG1.png");
		((ImageUI)renderer).setAnchorX(0.5f);
		((ImageUI)renderer).setAnchorY(0.5f);
	}
}
