package algo;

import src.manager.TileManager;
import system.physics.FlatVector;

public class AlgoNaif {

	public AlgoMatrice algoMatrice;
	public FlatVector me;
	public FlatVector objectif;
	public int[] me_coord;
	public int[] objectif_coord;

	public AlgoNaif(AlgoMatrice algoMatrice, FlatVector me, FlatVector objectif) {
		this.algoMatrice = algoMatrice;
		this.me = me;
		this.objectif = objectif;
		this.me_coord= TileManager.getTile(me);
		this.objectif_coord= TileManager.getTile(objectif);
	}

	public static boolean isValid(int[]coord, AlgoMatrice sousGrid) {
//		try {
//			return sousGrid.matrix[coord[0]][coord[1]] == 0;
//		} catch (ArrayIndexOutOfBoundsException e) {
//			// Handle the exception if the coordinates (x, y) are out of bounds
//			System.out.println("Coord bug : " + x + " " + y);
//
//			return false;
//		}
		return true;}

	public int[] NextPoint(AlgoMatrice sousGrid) {
		int horizontal = objectif_coord[0] -me_coord[0];
		int vertical = objectif_coord[1] -me_coord[1];
		int iMin = 0;
		int iMax = 6;
		int jMin = 0;
		int jMax = 6;
		int[] nextPoint = {3,3};
		int[] myPos = {3,3};
		if (vertical >0) {
			jMin = 3;
			nextPoint[1] = 6;	
		}
		if (vertical <0) {
			jMax = 3;
			nextPoint[1] = 0;
		}
		if (horizontal <0) {
			iMax = 3;
			nextPoint[0] =0;	
		}
		if (horizontal >0) {
			iMin = 3;
			nextPoint[0] = 6;	
		}
		//while isPathValid()
		
		return nextPoint;

	}

	public AlgoMatrice ChargeTag() {
		for (int i = 0; i < algoMatrice.length; i++) {
			for (int j = 0; j < algoMatrice.height; j++) {
				if (i == objectif_coord[0] && j == objectif_coord[1]) {
					algoMatrice.matrix[i][j].tag = 1;
				} else {
					algoMatrice.matrix[i][j].tag = 0;
				}
			}
		}
		return algoMatrice;
	}

	public boolean IsInSousGrid(AlgoMatrice sousGrid) {
		for (int i = 0; i < sousGrid.length; i++) {
			for (int j = 0; j < sousGrid.height; j++) {
				if (sousGrid.matrix[i][j].tag == 1) {
					return true;
				}

			}
		}
		return false;
	}
	
	public FlatVector toVector(int[] coord, AlgoMatrice sousGrid) {
		coord[0] += -31 -sousGrid.decalage_x;
		coord[1] += -7 -sousGrid.decalage_y;
		FlatVector dest = new FlatVector(coord[0], coord[1]);
		return dest;
	}
}
