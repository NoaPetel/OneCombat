package MainMenu.Button;

import system.GameSystem;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class PlayButton extends Button{

	public PlayButton(GameObject g, Transform t) {
		super(g, t);
		spritePath = "ressources/UI/Button/PlayButton.png";
		spriteHoverPath = "ressources/UI/Button/PlayButtonHover.png";
		loadSprites();
	}

	@Override
	public void onClick() {
		GameSystem.system.sceneManager.loadScene(1);
	}
	
}
