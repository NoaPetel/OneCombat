package Character.Components;

import system.object.component.animation.Animator;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CharacterLocomotion extends MonoBehavior {

	Animator animator;
	
	public CharacterLocomotion(GameObject g, Transform t) {
		super(g, t);
	}
	
	public void start() {
		animator = gameObject.getAnimator();
		idle();
	}
	
	private void playAnimation(String animationName) {
		if(animator == null) gameObject.getAnimator();
		animator.playAnimation(animationName);
	}
	
	public void idle() {
		playAnimation("Idle");
	}

	public void run() {
		playAnimation("Run");
	}
	
	public void jump(){
		playAnimation("Jump");
	}
	
	public void fall() {
		playAnimation("Fall");
	}
	
	public void dash() {
		playAnimation("Dash");
	}
	
	public void hit() {
		playAnimation("Hit");
	}
	
	public void death() {
		playAnimation("Death");
	}
	
	public void attack() {
		playAnimation("Attack");
	}
	
	public void longAttack() {
		playAnimation("LongAttack");
	}


	public void specialAttack() {
		playAnimation("SpecialAttack");
}
	public void shoot() {
		playAnimation("Shoot");
	}
	
	public void explode() {
		playAnimation("Explode");
	}
	
	public void teleport() {
		playAnimation("Teleport");
	}

}
