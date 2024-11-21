package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Hancock extends CharacterData{

	public Hancock() {
		super();
		name = "Hancock";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.8f;
		attackEffectTime = 0.5f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.7f, 0.5f);
		attackPosition = new FlatVector(0.7f, 0.1f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 0.7f;
		longAttackEffectTime = 0.4f;
		longAttackDamage = 40;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(1.8f, 0.7f);
		longAttackPosition = new FlatVector(0.25f, 0.15f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 1.8f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
