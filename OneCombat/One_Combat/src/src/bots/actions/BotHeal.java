package src.bots.actions;

import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class BotHeal extends MonoBehavior{

	private float onHealTime = 0f;
	private float healTime = 17f;
	
	private float AMOUNT_HEAL = 50f;
	
	//Components
	BotAttack BotAttack;
	BotThrow BotThrow;
	BotHealth BotHealth;
	
	public BotHeal(GameObject g, Transform t) {
		super(g, t);
	}
	
	public void start() {
		BotAttack = gameObject.getComponent(BotAttack.class);
		BotHealth = gameObject.getComponent(BotHealth.class);
		BotThrow = gameObject.getComponent(BotThrow.class);
	}
	
	public void update() {
		onHealTime -= Time.deltaTime;
	}


	public void Healing() {
		if(canHeal()) {
			this.BotHealth.addHealth(AMOUNT_HEAL);
			onHealTime = healTime;
		}
	}
	
	public boolean canHeal() {
		return onHealTime <= 0 && BotHealth.canBeMovable() && !BotThrow.isShooting && !BotAttack.isAttacking;
	}
	
		

}
