package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Luffy extends CharacterData{
	
	public Luffy() {
		super();
		name = "Luffy";
		//Attack
		attackGravityMult = 0f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.5f;
		attackEffectTime = 0.3f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(0.7f, 0.7f);
		attackPosition = new FlatVector(0.7f, -0.3f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 1.35f;
		longAttackEffectTime = 1.05f;
		longAttackDamage = 40;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(2.08f, 1.2f);
		longAttackPosition = new FlatVector(1.6f, -0.15f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 3f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
