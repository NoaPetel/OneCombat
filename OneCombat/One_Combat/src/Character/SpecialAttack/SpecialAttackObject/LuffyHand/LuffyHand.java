package Character.SpecialAttack.SpecialAttackObject.LuffyHand;

import system.object.component.animation.Animator;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class LuffyHand extends GameObject {

	public LuffyHand(FlatVector position, boolean dir, Rigidbody rb) {
		super();
		transform.setScale(2f);
		transform.setPosition(position);
		renderer = new SpriteRenderer(this, transform);
		((SpriteRenderer)renderer).setIsFlip(!dir);
		animator = new Animator(this, transform, "LuffyHand", (SpriteRenderer) renderer);
		components.add(new LuffyHandProjectil(this, transform, dir, rb));
	}
}
