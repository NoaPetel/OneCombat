package algo;

public class Matrice {
	int[][] matrice;

	public static int[][] transposeMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] transposedMatrix = new int[cols][rows];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				transposedMatrix[j][i] = matrix[i][j];
			}
		}

		return transposedMatrix;
	}

	public static void printMatrix(int[][] matrice) {
		//System.out.println("La carte : ");
//		matrice[14][14] = 3;
//		matrice[15][14] = 3;

		for (int i = 0; i < matrice[0].length; i++) {
			for (int j = 0; j < matrice.length; j++) {
				
				//System.out.print(matrice[j][i] + " ");
			}
			//System.out.println();
		}
	}

	public static int[][] normalise(int[][] grid) {
		int[][] newgrid = new int[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					newgrid[i][j] = 1;
				} else {
					newgrid[i][j] = 0;
				}
			}
		}
		
		printMatrix(newgrid);
		return newgrid;
	}
	public static boolean isValid(int x, int y, int[][] grid) {
	    try {
	        return grid[x][y] == 1;
	    } catch (ArrayIndexOutOfBoundsException e) {
	        // Handle the exception if the coordinates (x, y) are out of bounds
			System.out.println("Coord bug : " + x + " " + y);

	        return false;
	    }
	}


	public static void debugMatrix(int[][] matrice, int x, int y, int x_dest, int y_dest) {
	    try {
	        System.out.println("Debug : ");
	       

	        for (int i = 0; i < matrice[0].length; i++) {
	            for (int j = 0; j < matrice.length; j++) {
	            	if (i == y && j ==x) {
	            		System.out.print("3 ");
	            	}
	            	else if (i == y_dest && j ==x_dest) {
	            		System.out.print("4 ");
	            	}
	            	else {System.out.print(matrice[j][i] + " ");
	            }}
	            System.out.println();
	        }
	    } catch (ArrayIndexOutOfBoundsException e) {
	        // Handle the exception if the array indexes (x, y, x_dest, y_dest) are out of bounds
	        // Add your custom exception handling code here
	        e.printStackTrace(); // Or any other action you want to take
	    }
	}

	
}
