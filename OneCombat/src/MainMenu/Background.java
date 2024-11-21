package MainMenu;

import system.enumeration.SortingLayer;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class Background extends GameObject{
	
	public Background() {
		super();
		sortingLayer = SortingLayer.BackgroundUI;
		transform.setScale(2);
		renderer = new ImageUI(this, transform, "ressources/Menu/Background_menu.png");
		((ImageUI) renderer).setPivotPoint(0, 0);
	}
}
