package src.bots.actions;

import java.util.ArrayList;

import Interfaces.IDamagable;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ChienMove extends MonoBehavior{

	private int frameCounter;
	public float speed = 1f;
	private float moveInput;	// -1 s'il va a gauche, 1 s'il va a droite
	public boolean isFlip = true;		// true = regarde a droite | false = regarde a gauche
	private float timer = 0f;
	private float time = 2; // temsp en secondes
	private float h;
	private float w;
	private float DAMAGE = 10;
	
	//Compoenents
	BotLocomotion BotLocomotion;
	SpriteRenderer spriteRenderer;
	
	public ChienMove(GameObject gameObject, Transform transform, float H, float W) {
		super(gameObject, transform);
		this.moveInput = 1;
		this.frameCounter = 0;
		this.w = w;
		this.h = h;
	}
	
	public ChienMove(GameObject gameObject, Transform transform, int speed) {
		super(gameObject, transform);
		this.moveInput = 1;
		this.speed = speed;
		this.isFlip = true;
		this.frameCounter = 0;
	}
	
	@Override
	public void start() {
		BotLocomotion = gameObject.getComponent(BotLocomotion.class);
		spriteRenderer = (SpriteRenderer) gameObject.getRenderer();
	}
	
	@Override
	public void update() {
		//change input
		if(moveInput != 0) {
			checkDirectionToFace(moveInput < 0);
		}
		
		if(timer <=0) {
			if(this.moveInput == -1) {
				this.moveInput = 1;
			}
			else {this.moveInput = -1;}
			this.timer = time;
		}
		else {
			this.timer -= Time.deltaTime;
		}
		
		// hit ?
		
		this.run();
		}
	
	public void run() {
		float movement = Time.deltaTime * this.speed * this.moveInput;
		gameObject.getRigidbody().move(new FlatVector(movement, 0));
		if(movement != 0) {
			BotLocomotion.run();
		}
		/*else {
			characterLocomotion.idle();
		}*/
	}
	
	/***
	Turn methodes
	***/
	private void turn()
	{
		if (this.spriteRenderer != null) {
			this.isFlip = !isFlip;
			this.spriteRenderer.setIsFlip(!isFlip);
		}
	}
	
    public void checkDirectionToFace(boolean isMovingRight)
	{
		if (isMovingRight != isFlip && canTurn())
			turn();
	}

    private boolean canTurn()
    {
        return true;
    }
}
