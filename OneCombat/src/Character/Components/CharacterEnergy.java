package Character.Components;

import Interfaces.IEnergy;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatMath;

public class CharacterEnergy extends MonoBehavior implements IEnergy {
	
	// Parameters
	private float currentEnergy;

	// Components
	private CharacterData characterData;
    private CharacterUI characterUI;
	
    public CharacterEnergy(GameObject g, Transform t, CharacterData c) {
    	super(g, t);
    	characterData = c;
    }
    
    public void start() {
    	currentEnergy = characterData.startEnergy;
    	
    	characterUI = gameObject.getComponent(CharacterUI.class);
		characterUI.setupEnergy(currentEnergy, characterData.maxEnergy);
    }

	@Override
	public void addEnergy(float amount) {
		if(currentEnergy >= 0f) {
			currentEnergy += amount;
			currentEnergy = FlatMath.clamp(currentEnergy, 0f, characterData.maxEnergy);
			characterUI.setEnergy(currentEnergy);
		}
	}

	@Override
	public void useEnergy(float amount) {
		currentEnergy -= amount;
		currentEnergy = FlatMath.clamp(currentEnergy, 0f, characterData.maxEnergy);
		characterUI.setEnergy(currentEnergy);
	}

	@Override
	public boolean canUseEnergy(float amount) {
		return currentEnergy >= amount;
		
	}
}
