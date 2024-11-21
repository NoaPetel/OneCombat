package UI;

import UI.Components.EnergybarObject;
import UI.Components.HealthBar;
import UI.Components.HealthbarObject;
import system.GameSystem;
import system.object.component.renderer.ImageUI;
import system.object.gameobject.GameObject;

public class CharacterUIBackground extends GameObject {

	public static int NBHEALTHBARS = 0;
	public static float SPACEHEALTHBAR = 310;
	public static float SCACEVERTICALHEALTHBAR = 25; 
	public static float SCALE = 1.2f;
	public static float SCALESMALL = 0.6f;
	
	private HealthbarObject hb;
	private EnergybarObject eb;

	public CharacterUIBackground(String path) {
		super();
		float x = 10 + SPACEHEALTHBAR * NBHEALTHBARS;
		float y = 10;
		float scale = SCALE;
		boolean isSmall = false;
		renderer = new ImageUI(this, transform, path);
		transform.setScale(SCALE);
		transform.setPositionX(x);
		transform.setPositionY(y);
		
		NBHEALTHBARS++;
		if(NBHEALTHBARS > 4) {
			x = 10;
			y = 35 + SCACEVERTICALHEALTHBAR * (NBHEALTHBARS - 4);
			transform.setScale(SCALESMALL);
			transform.setPositionX(x);
			transform.setPositionY(y);
			scale = SCALESMALL;
			isSmall = true;
		}
		
		hb = new HealthbarObject(x, y, scale, isSmall);
		eb = new EnergybarObject(x, y, scale, isSmall);
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(hb);
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(eb);
	}

	public HealthBar getHealthbar() {
		return hb.getComponent(HealthBar.class);
	}

	public HealthBar getEnergybar() {
		return eb.getComponent(HealthBar.class);
	}
}
