package system.scene;

import java.awt.Graphics;

import src.map.Tile.Tile;
import system.object.gameobject.GameObject;

public interface IScene {
	
	public void load();
	public void start();
	public void update();
	public void end();
	public void render(Graphics g);
	public void addGameObject(GameObject go);
	public void removeGameObject(GameObject go);
}
