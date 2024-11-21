package Character.Components;

import UI.CharacterUIBackground;
import UI.Components.HealthBar;
import system.GameSystem;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CharacterUI extends MonoBehavior {

	private String pathFolder = "ressources/UI/Healthbar";

	private HealthBar healthbar;
	private HealthBar energybar;
	private CharacterUIBackground uiBackground;

	public CharacterUI(GameObject g, Transform t, String uiName) {
		super(g, t);
		
		uiBackground = new CharacterUIBackground(pathFolder + uiName + ".png");
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(uiBackground);
	}
	
	public void start() {
		healthbar = uiBackground.getHealthbar();
		energybar = uiBackground.getEnergybar();
	}
	
	public void setupEnergy(float energy, float maxEnergy) {
		if(energybar == null) energybar = uiBackground.getEnergybar();
		
		energybar.setup(maxEnergy, energy);
	}
	
	public void setupHealth(float health, float maxHealth) {
		if(healthbar == null) healthbar = uiBackground.getHealthbar();
		
		healthbar.setup(maxHealth, health);
	}
	
	public void setHealth(float amount)
    {
		healthbar.setValue(amount);
    }

    public void setEnergy(float amount)
    {
    	energybar.setValue(amount);
    }

}
