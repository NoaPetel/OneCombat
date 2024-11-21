package Character.Data;

import Character.Components.CharacterData;
import system.physics.FlatVector;

public class Vivi extends CharacterData{
	public Vivi() {
		super();
		name = "Vivi";
		//Attack
		attackGravityMult = 5f;
		//Simple Attack
		attackInputBufferTime = 0.1f;
		attackTime = 0.7f;
		attackEffectTime = 0.3f;
		attackDamage = 20;
		attackEnergy = 0f;
		attackRange = new FlatVector(1.6f, 0.8f);
		attackPosition = new FlatVector(0.45f, -0.1f);
		//Long Attack
		longAttackInputBufferTime = 0.1f;
		longAttackTime = 1.1f;
		longAttackEffectTime = 0.55f;
		longAttackDamage = 40;
		longAttackEnergy = 0;
		longAttackRange = new FlatVector(0.9f, 1.1f);
		longAttackPosition = new FlatVector(1.25f, -0.1f);
		//Special Attack
		specialAttackInputBufferTime = 0.1f;
		specialAttackTime = 1.2f;
		specialAttackEnergy = 0f;
		
		onValidate();
	}
}
