package UI.Components;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.RectRendererUI;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class HealthBar extends MonoBehavior {

	//Components
	RectRendererUI slider;
	
	private float maxValue;
	private float currentValue;
	private float sliderWidth;
	
	public HealthBar(GameObject g, Transform t, RectRendererUI renderer) {
		super(g, t);
		slider = renderer;
		sliderWidth = slider.getWidth();
	}
	
	public void setup(float maxValue, float value) {
		this.maxValue = maxValue;
		setValue(value);
	}
	
	public void setValue(float amount) {
		currentValue = amount;
		//Update image
		float width = currentValue / maxValue * sliderWidth;
		slider.setWidth(width);
	}

}
