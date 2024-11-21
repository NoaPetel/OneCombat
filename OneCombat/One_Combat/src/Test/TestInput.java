package Test;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class TestInput extends MonoBehavior {

	protected TestInput characterMovement;
	protected int keycodeLeft = 38;
	
	public TestInput(GameObject g, Transform t) {
		super(g, t);
	}
	
	
	public TestInput(GameObject g, Transform t, int k1) {
		super(g, t);
		keycodeLeft = k1;
	}
	
	@Override
	public void start() {
		//characterMovement = gameObject.getComponent(CharacterMovement.class);
	}

	@Override
	public void update() {
		/*float input = 0;
		if (InputManager.IsPressed(keycodeLeft))
			input += -1;
		if (InputManager.IsPressed(keycodeRight))
			input += 1;
		if (InputManager.IsPressed(keycodeJump)) {
			characterMovement.OnJumpInput();
		}
		characterMovement.SetInput(input, 0);*/
	}

}
