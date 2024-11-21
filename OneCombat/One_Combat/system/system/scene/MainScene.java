package system.scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import system.GameSystem;
import system.object.gameobject.GameObject;

public class MainScene implements IScene {

	protected ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	protected ArrayList<GameObject> tmp = new ArrayList<GameObject>();
	protected ArrayList<GameObject> tmpRemove = new ArrayList<GameObject>();

	@Override
	public void load() {
		
	}
	
	@Override
	public void start() {
		//Update Sorting Layer
		Collections.sort(gameObjects);
		for(GameObject g : gameObjects) {
			g.start();
		}
	}

	@Override
	public void update() {
		if(tmp.size() > 0) {
			for(GameObject g : tmp) {
				g.start();
				gameObjects.add(g);
				//Update Sorting Layer
			}
			Collections.sort(gameObjects);
			tmp.clear();
		}
		if(tmpRemove.size() > 0) {
			for(GameObject g : tmpRemove) {
				gameObjects.remove(g);
			}
			tmpRemove.clear();
		}
		for(GameObject g : gameObjects) {
			g.update();
		}
	}

	@Override
	public void render(Graphics g) {
		for(GameObject gb : gameObjects) {
			gb.render(g);
		}
	}

	@Override
	public void end() {
		gameObjects.clear();
		tmp.clear();
		tmpRemove.clear();
		GameSystem.system.physics.clearScene();
		
	}
	
	@Override
	public void addGameObject(GameObject go) {
		this.tmp.add(go);
	}
	
	@Override
	public void removeGameObject(GameObject go) {
		this.tmpRemove.add(go);
	}


}
