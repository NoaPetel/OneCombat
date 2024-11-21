package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Robin extends CharacterData{
	
	public Robin() {
		super();
		name = "Robin";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 1.2f;
		attackEffectTime = 0.6f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.8f, 1f);
		attackPosition = new FlatVector(0.95f, -0.2f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 2.6f;
		longAttackEffectTime = 2.2f;
		longAttackDamage = 40;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(5f, 0.7f);
		longAttackPosition = new FlatVector(2.5f, 0.3f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 2.1f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
