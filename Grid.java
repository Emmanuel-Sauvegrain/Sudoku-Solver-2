package App;

public class Grid {

/*
	// Suddoku simple: 50 millisecondes avec Backtracking seul
	private int[][] actualGrid = {
			{0,0,0, 0,2,0, 5,0,8},
			{0,0,6, 4,1,9, 7,0,2},
			{0,3,7, 8,0,0, 4,1,0},
			{0,0,0, 0,0,4, 6,0,0},
			{6,1,0, 9,0,2, 0,5,4},
			{0,0,3, 5,0,0, 0,0,0},
			{0,7,9, 0,0,5, 1,2,0},
			{5,0,1, 2,9,7, 8,0,0},
			{3,0,2, 0,4,0, 0,0,0} };
*/
	// Soddoku complexe: 3226 millisecondes avec backtracking seul
	private int[][] actualGrid = {
			{0,3,1, 0,0,2, 0,0,8},
			{6,0,0, 0,8,0, 0,0,0},
			{0,0,0, 0,0,0, 0,3,1},
			{7,5,0, 0,0,0, 0,6,9},
			{0,9,0, 0,1,0, 0,2,0},
			{0,0,8, 0,3,6, 0,0,0},
			{9,0,0, 0,0,5, 0,0,0},
			{0,7,0, 2,0,0, 0,0,0},
			{1,0,0, 6,0,0, 4,0,0} };

	
	
	private int gridSize = 9;
	private int sizeBox = 3;
	
	public Grid() {
		
	}	
	
	
	
	
	public boolean checkRow(int row,int number,int [][]checkGrid) {
		for(int i=0; i<this.gridSize; i++) {
			if(number == checkGrid[row][i]) {
				return false;
			}			
		}
		return true;
	}
	
	public boolean checkCol(int col,int number,int [][]checkGrid) {
		for(int i=0; i<this.gridSize; i++) {
			
			if(number == checkGrid[i][col]) {
				return false;
			}			
		}
		return true;
	}
	
	
	public boolean checkBox( int row, int col, int number,int [][]checkGrid) {
		row = (row / this.sizeBox) * this.sizeBox ;
		col = (col / this.sizeBox) * this.sizeBox ;
		
		for(int i=0;i<this.sizeBox;i++) {
			for(int j=0;j<this.sizeBox;j++) {
				if( checkGrid[row+i][col+j] == number) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[][] getActualGrid(){
		return this.actualGrid;
	}
	
	public void setActualGrid(int row,int col,int value) {
		actualGrid[row][col] = value;		
	}
	
	
	
	
	
	public void displayActualGrid() {
		System.out.println("\n");
		System.out.print("-----------------------------------------");
		for (int i = 0; i < 9; i++) {
            System.out.println();
            if(i%3==0)
                System.out.print("\n");
        for (int j = 0; j < 9; j++) {
            if (j % 3 == 0)
                System.out.print(" ");
            if (actualGrid[i][j] == 0)
                System.out.print(". ");
            if (actualGrid[i][j] == 1)
                System.out.print("1 ");
            if (actualGrid[i][j] == 2)
                System.out.print("2 ");
            if (actualGrid[i][j] == 3)
                System.out.print("3 ");
            if (actualGrid[i][j] == 4)
                System.out.print("4 ");
            if (actualGrid[i][j] == 5)
                System.out.print("5 ");
            if (actualGrid[i][j] == 6)
                System.out.print("6 ");
            if (actualGrid[i][j] == 7)
                System.out.print("7 ");
            if (actualGrid[i][j] == 8)
                System.out.print("8 ");
            if (actualGrid[i][j] == 9)
                System.out.print("9 ");
        	}
		}
	}
	
	
		
}
