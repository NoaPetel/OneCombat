package src.bots.actions;

import Interfaces.IDamagable;
import src.bots.Chien.Chien;
import src.items.Item;
import src.manager.BotManager;
import system.GameSystem;
import system.Time;
import system.enumeration.ItemType;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class BotHealth extends MonoBehavior implements IDamagable {

	float health;
	float max_health;
	int type;
	
	private float damageTime = 0.3f;
	private float onDamageTime;
	
	private float deathTime = 3f;
	private float onDeathTime;
	
	BotLocomotion BotLocomotion;

	public BotHealth(GameObject g, Transform transform, float health, int type) {
		super(g, transform);
		this.health = health;
		this.max_health = health;
		this.type = type;
	}
	
	public void start() {
		BotLocomotion = gameObject.getComponent(BotLocomotion.class);
	}
	
	public void update() {
		onDamageTime -= Time.deltaTime;
		onDeathTime -= Time.deltaTime;
		
		
		if(health <= 0 && onDeathTime <= 0) {
			death();
		}
	}
	

	@Override
	public void setDamage(float amount) {
		
		if(!canTakeDamage()) return;
		
		this.health -= amount;
		if (this.health <= 0) {
			this.onDeath();
		}
		else {
			hit();
		}

	}
	
	public void hit() {
		BotLocomotion.hit();
		onDamageTime = damageTime;
	}
	
	public void onDeath() {
		BotLocomotion.death();
		onDeathTime = deathTime;
	}

	@Override
	public void death() {
				
		BotManager.instance.BotDeath(this.type);
		this.destroy();

		float random;
		if (this.gameObject instanceof Chien) {
			random = 0.2f;
		} else {
			random = (float) Math.random();
		}
		ItemType typeChoice;
		if (random > 0.5) {
			typeChoice = ItemType.Health;
		} else {
			typeChoice = ItemType.Energy;
		}

		GameSystem.system.sceneManager.getCurrentScene()
				.addGameObject(Item.CreateItem(gameObject.transform.getPosition(), 1f, typeChoice, false));

	}

	@Override
	public void addHealth(float amount) {
		
		if(!isAlive()) return;
		
		if (this.max_health > (this.health + amount)) {
			this.health += amount;
		} else {
			this.health = this.max_health;
		}

	}

	@Override
	public boolean canTakeDamage() {
		return isAlive() && !isHiting();
	}

	public boolean isHiting() {
		return onDamageTime > 0;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public boolean canBeMovable() {
		return !isHiting() && isAlive();
	}

}
