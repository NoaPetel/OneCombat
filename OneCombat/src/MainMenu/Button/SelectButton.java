package MainMenu.Button;

import system.GameSystem;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class SelectButton extends Button {
	
	private PlayerSelect playerSelect;
	private String choice;
	
	public SelectButton(GameObject g, Transform t, PlayerSelect playerSelect, String choice) {
		super(g, t);
		spritePath = "ressources/UI/Select/" + choice + ".png";
		spriteHoverPath = "ressources/UI/Select/" + choice + "Hover.png";
		loadSprites();
		
		this.choice = choice;
		this.playerSelect = playerSelect;
	}

	@Override
	public void onClick() {
		playerSelect.setSelect(choice);
	}
}
