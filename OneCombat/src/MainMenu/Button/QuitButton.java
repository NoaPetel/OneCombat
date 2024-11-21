package MainMenu.Button;

import system.GameSystem;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class QuitButton extends Button{

	public QuitButton(GameObject g, Transform t) {
		super(g, t);
		spritePath = "ressources/UI/Button/QuitButton.png";
		spriteHoverPath = "ressources/UI/Button/QuitButtonHover.png";
		loadSprites();
	}

	@Override
	public void onClick() {
		GameSystem.system.exit();
	}
	
}
