package Character.Data;

import system.physics.FlatVector;
import Character.Components.CharacterData;

public class Sanji extends CharacterData{
	
	public Sanji() {
		super();
		name = "Sanji";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.7f;
		attackEffectTime = 0.4f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.8f, 0.6f);
		attackPosition = new FlatVector(0.7f, -0.2f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 1.2f;
		longAttackEffectTime = 0.55f;
		longAttackDamage = 20;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(1.85f, 0.7f);
		longAttackPosition = new FlatVector(1.3f, -0.15f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 4.2f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
