package scenes;

import Test.BackgroundObject;
import Test.TestObject;
import UI.CharacterUIBackground;
import camera.CameraObject;
import src.Ronces.Ronces;
import src.items.Item;
import src.manager.object.ManagerObject;
import system.enumeration.ItemType;
import system.physics.FlatVector;
import system.scene.MainScene;

public class TestScene1 extends MainScene {
	// Add GameObject in Constructeur
	@Override
	public void load() {
		try {
			gameObjects.add(new BackgroundObject());
			CharacterUIBackground.NBHEALTHBARS = 0;
			gameObjects.add(new ManagerObject());
			gameObjects.add(new TestObject());
			//gameObjects.add(new BasicPlatform(2, -2, Color.blue));
			gameObjects.add(new CameraObject());
			//gameObjects.add(new BasicPlatform(2, 0, Color.green));
			//gameObjects.add(new Item(ItemType.Speed, true, new FlatVector(0, 0)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
