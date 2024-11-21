package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Shanks extends CharacterData{
	
	public Shanks() {
		super();
		name = "Shanks";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.8f;
		attackEffectTime = 0.3f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.8f, 1.4f);
		attackPosition = new FlatVector(0.7f, -0.2f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 0.7f;
		longAttackEffectTime = 0.35f;
		longAttackDamage = 20;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(2f, 2f);
		longAttackPosition = new FlatVector(0f, -0.3f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 1.2f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
