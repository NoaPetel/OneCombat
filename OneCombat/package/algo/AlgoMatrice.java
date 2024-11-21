package algo;

import system.physics.FlatVector;

public class AlgoMatrice {

	public AlgoPoint[][] matrix;
	public int length;
	public int height;
	public int decalage_x;
	public int decalage_y;

	public AlgoMatrice(int length, int height, int decalage_x, int decalage_y) {
		this.height = height;
		this.length = length;
		this.decalage_x = decalage_x;
		this.decalage_y = decalage_y;
		this.matrix = new AlgoPoint[length][height];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = new AlgoPoint(0, 0);
			}
		}
	}

	public int getLength() {
		return this.length;
	}

	public int getHeight() {
		return this.height;
	}

	public static AlgoMatrice normalise(int[][] grid) {
		int[][] newgrid = new int[grid.length][grid[0].length];
		AlgoMatrice newMatrice = new AlgoMatrice(grid.length, grid[0].length, 0, 0);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					newMatrice.matrix[i][j].valeur = 0;
				} else {
					newMatrice.matrix[i][j].valeur = 1;
				}
			}
		}

		return newMatrice;
	}

	public void printAlgoMatrice(AlgoMatrice algoMatrice) {

		for (int i = 0; i < algoMatrice.length; i++) {
			for (int j = 0; j < algoMatrice.height; j++) {
				System.out.println(algoMatrice.matrix[i][j].valeur);
			}
			return;
		}

	}
	public AlgoMatrice SousGrid(AlgoMatrice algoMatrice, int[] me_coord) {
	    int dec_x = (int) (me_coord[0] - 3);
	    int dec_y = (int) (me_coord[1]- 3);
	    
	    try {
	        AlgoMatrice sousMatrice = new AlgoMatrice(6, 6, dec_x, dec_y);
	        
	        for (int i = 0; i < sousMatrice.length; i++) {
	            for (int j = 0; j < sousMatrice.height; j++) {
	                sousMatrice.matrix[i][j].valeur = algoMatrice.matrix[dec_x + i][dec_y + j].valeur;
	                sousMatrice.matrix[i][j].tag = algoMatrice.matrix[dec_x + i][dec_y + j].tag;
	            }
	        }
	        
	        return sousMatrice;
	    } catch (ArrayIndexOutOfBoundsException e) {
	        System.out.println("Out of bounds");
	        return null; 
	    }
	}

	

}
