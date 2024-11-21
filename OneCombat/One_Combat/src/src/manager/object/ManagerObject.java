package src.manager.object;

import java.io.IOException;

import src.manager.BotManager;
import src.manager.GameManager;
import src.manager.ItemManager;
import src.manager.PlatformManager;
import src.manager.PortalManager;
import src.manager.TileManager;
import system.Constante;
import system.object.gameobject.GameObject;

public class ManagerObject extends GameObject {
	
	
	public ManagerObject() throws IOException {
		components.add(new TileManager(this, transform, Constante.NAME_MAP,Constante.MAP_LENGTH,Constante.MAP_HEIGHT));
		components.add(new GameManager(this, transform));
		components.add(new ItemManager(this, transform));
		components.add(new BotManager(this, transform, Constante.MAP_LENGTH, Constante.MAP_HEIGHT));
		components.add(new PortalManager(this, transform));
		components.add(new PlatformManager(this, transform));
	}
}
