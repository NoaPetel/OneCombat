package system.object.component.animation;

import java.awt.image.BufferedImage;

public class Animation {
	
	protected BufferedImage[] animationSprites;
	protected float[] frameTimers;
	protected String name;
	protected boolean isLooping;
	protected int pivotX;
	protected int pivotY;
	
	public Animation(BufferedImage[] animationSprites, float[] frameTimers, String name, boolean isLooping, int pivotX, int pivotY) {
		this.animationSprites = animationSprites;
		this.frameTimers = frameTimers;
		this.name = name;
		this.isLooping = isLooping;
		this.pivotX = pivotX;
		this.pivotY = pivotY;
	}
	
	public BufferedImage getSprite(int index) {
		return animationSprites[index];
	}
	
	public float getFrameTimer(int index) {
		return frameTimers[index];
	}
	
	public boolean isLastPassedFrame(int index) {
		return index == animationSprites.length;
	}
	
}
