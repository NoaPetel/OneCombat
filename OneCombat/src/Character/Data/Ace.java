package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Ace extends CharacterData {
	
	public Ace() {
		super();
		name = "Ace";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.5f;
		attackEffectTime = 0.25f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.6f, 1f);
		attackPosition = new FlatVector(0.5f, -0.3f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 0.83f;
		longAttackEffectTime = 0.6f;
		longAttackDamage = 20;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(2.08f, 0.7f);
		longAttackPosition = new FlatVector(1.7f, -0.3f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 2.7f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
