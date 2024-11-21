package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Usopp extends CharacterData{
	
	public Usopp() {
		super();
		name = "Usopp";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 1.2f;
		attackEffectTime = 0.5f;
		attackDamage = 10;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.7f, 1.5f);
		attackPosition = new FlatVector(0.65f, -0.3f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 1.4f;
		longAttackEffectTime = 0.7f;
		longAttackDamage = 30;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(1.3f, 0.8f);
		longAttackPosition = new FlatVector(0.2f, -0.4f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 2.5f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
