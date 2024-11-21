package Character.Components;

import system.InputManager;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class CharacterInput extends MonoBehavior {

	protected CharacterMovement characterMovement;
	protected CharacterAttack characterAttack;
	protected CharacterHealth characterHealth;

	protected int keycodeLeft = 37;
	protected int keycodeRight = 39;
	protected int keycodeJump = 38;
	protected int keycodeUpJump = 40;
	protected int keycodeDash = 87;	
	protected int keycodeAttack = 88;
	protected int keycodeLongAttack = 67;
	protected int keycodeSpecialAttack = 66;
	protected int keycodeHit = 86;

	public CharacterInput(GameObject g, Transform t) {
		super(g, t);
	}

	public CharacterInput(GameObject g, Transform t, int k1, int k2, int k3) {
		super(g, t);
		keycodeLeft = k1;
		keycodeRight = k2;
		keycodeJump = k3;
	}

	@Override
	public void start() {
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterHealth = gameObject.getComponent(CharacterHealth.class);
	}

	@Override
	public void update() {
		
		FlatVector input = FlatVector.Zero();
		
		if (InputManager.IsPressed(keycodeLeft)) {
			input.x += -1;
		}
		
		if (InputManager.IsPressed(keycodeRight)) {
			input.x += 1;
		}
		
		if (InputManager.IsPressed(keycodeJump)) {
			input.y += -1;
			characterMovement.onJumpInput();
		}
		
		if (InputManager.IsPressed(keycodeUpJump)) {
			characterMovement.onJumpUpInput();
			input.y += 1;
		}
		
		if(InputManager.IsPressed(keycodeDash)) {
			characterMovement.onDashInput();
		}
		
		if (InputManager.IsPressed(keycodeAttack)) {
			characterAttack.onAttackInput();
		}
		
		if(InputManager.IsPressed(keycodeLongAttack)) {
			characterAttack.onLongAttackInput();
		}
		
		if(InputManager.IsPressed(keycodeSpecialAttack)) {
			characterAttack.onSpecialAttackInput();
		}	
		
		if(InputManager.IsPressed(keycodeHit)) {
			characterHealth.setDamage(10);
		}	
		
		if(characterMovement != null) 
			characterMovement.setInputVector(input);
		
		//System.out.println(InputManager.keysEvent);
	}

}
