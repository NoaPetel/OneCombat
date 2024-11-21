package scenes;

import MainMenu.Background;
import MainMenu.Button.PlayButtonObject;
import MainMenu.Button.PlayerSelect;
import MainMenu.Button.QuitButtonObject;
import system.scene.MainScene;

public class MainMenu extends MainScene {
	//Add GameObject in Constructeur
	 @Override
	    public void load() {
		 gameObjects.add(new Background());
		 gameObjects.add(new PlayButtonObject());
		 gameObjects.add(new QuitButtonObject());
		 gameObjects.add(new PlayerSelect(0));
		 gameObjects.add(new PlayerSelect(1));
	 }
}
