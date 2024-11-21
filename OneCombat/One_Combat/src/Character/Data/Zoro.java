package Character.Data;

import Character.Components.*;
import system.physics.FlatVector;

public class Zoro extends CharacterData {

	public Zoro() {
		super();
		name = "Zoro";
		//Attack
		attackGravityMult = 5f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.7f;
		attackEffectTime = 0.35f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.8f, 1.2f);
		attackPosition = new FlatVector(0.7f, -0.2f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 0.9f;
		longAttackEffectTime = 0.6f;
		longAttackDamage = 20;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(1.6f, 1.4f);
		longAttackPosition = new FlatVector(2f, -1f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 1.7f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
