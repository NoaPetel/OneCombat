package src.manager;

import java.io.IOException;

import src.portal.Portal;
import system.GameSystem;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.scene.IScene;

public class PortalManager extends MonoBehavior {
	
	int[][] fixed = {{-18, 24, -24, 17},{2, 6, 11, 15}};
	
	public PortalManager(GameObject g, Transform t) throws IOException {
		super(g, t);
		createPortals();
	}
	
	public void createPortals() {
		
		for(int i = 0; i < 4; i += 2)
		{
			Portal last = createPortal(i);
			Portal portal = createPortal(i+1);
			
			last.twin = portal;
			portal.twin = last;	

			addPortal(last);
			addPortal(portal);
		}
	}
	
	public void addPortal(Portal portal)
	{
		if(portal!= null) {
			IScene sc = GameSystem.system.sceneManager.getCurrentScene();
			sc.addGameObject(portal);
		}
	}
	
	public Portal createPortal(int position) {
	    Portal portal = null;
	    
	    int x = this.fixed[0][position];
	    int y = this.fixed[1][position];
	    
	    int direction = 1;
	    
	    if(position != 1)
	    {
	    	direction = -1;
	    }
	    
	    try {
	        portal = new Portal(x, y, direction);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return portal;
	}

}
