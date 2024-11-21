package UI.Components;

import java.awt.Color;

import system.object.component.renderer.RectRendererUI;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class EnergybarObject extends GameObject{
	
	public static float deltaX = 38;
	public static float deltaY = 20;
	public static float deltaSmallX = 20;
	public static float deltaSmallY = 10;
	public static float width = 182;
	public static float height = 5;
	
	public EnergybarObject(float x, float y, float scale, boolean isSmall) {
		super();
		transform.setScale(scale);
		if(!isSmall)
			transform.setPosition(new FlatVector(deltaX + x, deltaY + y));
		else
			transform.setPosition(new FlatVector(deltaSmallX + x, deltaSmallY + y));
		renderer = new RectRendererUI(this, transform, Color.blue, width, height);
		components.add(new HealthBar(this, transform, (RectRendererUI)renderer));
	}
}
