package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Nami extends CharacterData{
	
	public Nami() {
		super();
		name = "Nami";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.7f;
		attackEffectTime = 0.4f;
		attackDamage = 10;
		attackEnergy = 0f;
		attackRange = new FlatVector(1.5f, 1.5f);
		attackPosition = new FlatVector(0.65f, -0.3f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 1.2f;
		longAttackEffectTime = 0.7f;
		longAttackDamage = 30;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(1.5f, 1.8f);
		longAttackPosition = new FlatVector(1f, -0.3f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 2.45f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
