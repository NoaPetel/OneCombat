package system.object.component.animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import system.GameSystem;
import system.Time;
import system.object.component.Component;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class Animator extends Component  {
	
	private HashMap<String, Animation> animations;
	private Animation currentAnimation;
	private String currentAnimationName;
	private SpriteRenderer spriteRenderer;
	private BufferedImage currentAnimationSprite;
	private int currentIndex;
	private float currentTimer;
	
	public Animator(GameObject g, Transform t, String animationsName, SpriteRenderer s) {
		super(g, t);
		spriteRenderer = s;
		animations = GameSystem.system.animationManager.getAnimations(animationsName);
	}
	
	
	public void update() {
		currentTimer -= Time.deltaTime;
		
		if(currentAnimation == null) return;
		if(currentTimer <= 0) {
			if(currentAnimation == null) return;
			if(currentAnimation.isLastPassedFrame(currentIndex+1)) {
				if(currentAnimation.isLooping) {
					setNewSprite(0);
				} else {
					playAnimation("Idle");
				}
			} else {
				setNewSprite(currentIndex+1);
			}
		}
		
	}
	
	public void playAnimation(String animationName) {
		if(animationName == currentAnimationName||spriteRenderer == null) return;
		if(!animations.containsKey(animationName)) animationName = "Idle";
		
		currentAnimationName = animationName;
		currentAnimation = animations.get(currentAnimationName);
		spriteRenderer.setPivotPoint(currentAnimation.pivotX, currentAnimation.pivotY);
		currentTimer = 0;
		
		setNewSprite(0);
	}
	
	private void setNewSprite(int index) {
		currentIndex = index;
		currentTimer += currentAnimation.getFrameTimer(currentIndex);
		currentAnimationSprite = currentAnimation.getSprite(currentIndex);
		spriteRenderer.setSprite(currentAnimationSprite);
	}
	
}
