package src.bots.Chien;

import java.io.IOException;

import EntityComponent.Entity;
import EntityComponent.EntityHandler;
import src.bots.BotAction;
import src.bots.BotCondition;
import src.bots.actions.BotAttack;
import src.bots.actions.BotHeal;
import src.bots.actions.BotHealth;
import src.bots.actions.BotHit;
import src.bots.actions.BotLocomotion;
import src.bots.actions.BotMovement;
import src.bots.actions.BotThrow;
import src.manager.BotManager;
import system.enumeration.Layer;
import system.object.component.animation.Animator;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;

public class Chien extends GameObject {

	private float HEALTH = 30f;
	private float scale = 2.5f;
	private float width = 0.66f;
	private float height = 0.32f;
	public int type = BotManager.Chien;

	public Chien() throws IOException {
		super();
		this.width *= scale;
		this.height *= scale;
		this.transform.setScale(scale);
		this.transform.setPositionY(-10);
		this.renderer = new SpriteRenderer(this, this.transform);
		this.animator = new Animator(this, this.transform, "Dog", (SpriteRenderer) this.renderer);
		this.rb = Rigidbody.CreateBoxBody(1f, width, height, 1, false, 0, Layer.WeakBot, this, this.transform);// 0.5
																													// pour
																													// collision
		this.components.add(new BotLocomotion(this, transform));
		this.components.add(new BotMovement(this, this.transform));
		this.components.add(new BotAttack(this, this.transform));
		this.components.add(new BotHealth(this, this.transform, HEALTH, type));
		this.components.add(new BotHeal(this, this.transform));
		this.components.add(new BotThrow(this, this.transform));
		this.components.add(new BotHit(this, this.transform, width, height));
		this.components.add(new EntityHandler(this, this.transform));
		this.components.add(new Entity(this, this.transform, "Chien"));
		this.components.add(new BotAction(this, this.transform));
		this.components.add(new BotCondition(this, this.transform));
	}

	public Chien(int x, int y) throws IOException {
		this();
		this.transform.setPositionY(y);
		this.transform.setPositionX(x);
	}
}