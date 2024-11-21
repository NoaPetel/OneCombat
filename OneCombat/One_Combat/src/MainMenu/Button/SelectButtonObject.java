package MainMenu.Button;

import system.enumeration.SortingLayer;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class SelectButtonObject extends GameObject {
	
	public float scale = 1f;
	
	public SelectButtonObject(PlayerSelect playerSelect, float anchorX, float anchorY, String choice) {
		super();
		this.sortingLayer = SortingLayer.UI;
		transform.setScale(scale);
		renderer = new ImageUI(this, transform);
		((ImageUI)renderer).setAnchorX(anchorX);
		((ImageUI)renderer).setAnchorY(anchorY);
		components.add(new SelectButton(this, transform, playerSelect, choice));
	}
}
