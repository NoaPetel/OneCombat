package system.physics;

import system.enumeration.Layer;

public class CollisionMatrice {

	public static int[][] matrice = { 
			
			{ 1, 1, 0, 1, 1, 0, 0, 0, 1 },
			{ 1, 1, 0, 1, 1, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 1, 1, 0, 0, 0, 0, 0, 0, 1 }, 
			{ 1, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 0, 1, 1, 0, 1, 0, 1 } 
			
	};
	
	public static boolean isCollide(Layer la, Layer lb) {
		return matrice[la.getValue()][lb.getValue()] == 1;
	}
}
