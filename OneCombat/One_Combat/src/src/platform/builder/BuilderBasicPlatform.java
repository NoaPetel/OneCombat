package src.platform.builder;

import java.awt.Color;
import java.io.IOException;

import src.platform.BasicPlatform;
import src.platform.actions.PlatformHeal;
import system.GameSystem;

public class BuilderBasicPlatform implements PlatformBuilder{
	Color color;
	
	public BuilderBasicPlatform(Color color) {
		this.color = color;
	}

	public BasicPlatform build() throws IOException{
		BasicPlatform result = new BasicPlatform(this.color);
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(result);
		return result;
	}
	
	public BasicPlatform build(float x, float y) throws IOException{
		BasicPlatform result = new BasicPlatform(x, y, this.color);
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(result);
		return result;
	}
	
	public BasicPlatform build(float x, float y, float width, float height) throws IOException{
		BasicPlatform result = new BasicPlatform(x, y, this.color, width, height);
		GameSystem.system.sceneManager.getCurrentScene().addGameObject(result);
		return result;
	}
}
