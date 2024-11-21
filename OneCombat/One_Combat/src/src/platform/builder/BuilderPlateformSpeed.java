package src.platform.builder;

import java.awt.Color;
import java.io.IOException;

import src.platform.BasicPlatform;
import src.platform.actions.PlatformHeal;
import src.platform.actions.PlatformSpeed;
import system.GameSystem;

public class BuilderPlateformSpeed implements PlatformBuilder{
	Color color;
	
	public BuilderPlateformSpeed(Color color) {
		this.color = color;
	}
	
	public BasicPlatform build() throws IOException{
		BasicPlatform result = new BasicPlatform(this.color);
		result.addComponents(new PlatformSpeed(result, result.transform));
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(result);
		return result;
	}
	
	public BasicPlatform build(float x, float y) throws IOException{
		BasicPlatform result = new BasicPlatform(x, y, this.color);
		result.addComponents(new PlatformSpeed(result, result.transform));
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(result);
		return result;
	}
	
	public BasicPlatform build(float x, float y, float width, float height) throws IOException{
		BasicPlatform result = new BasicPlatform(x, y, this.color, width, height);
		result.addComponents(new PlatformSpeed(result, result.transform));
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(result);
		return result;
	}
}
