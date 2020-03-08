package App;

import java.util.ArrayList;
import java.util.Arrays;

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


	public static int[][] deepCopyOfGrid(int[][] grid){
        int[][] newGrid = new int[9][9];
        for (int i = 0; i < 9; i++)
            newGrid[i] = Arrays.copyOf(grid[i], 9);
        return newGrid;
	}
	
	public static boolean checkRow(int row,int number, int[][] grid) {
		for(int i=0; i<9; i++) {
			if(number == grid[row][i]) {
				return false;
			}			
		}
		return true;
	}
	
	public static boolean checkCol(int col,int number, int[][] grid) {
		for(int i=0; i<9; i++) {
			
			if(number == grid[i][col]) {
				return false;
			}			
		}
		return true;
	}
	
	
	public static boolean checkBox( int row, int col, int number, int[][] grid) {
		row = (row / 3) * 3 ;
		col = (col / 3) * 3 ;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if( grid[row+i][col+j] == number) {
					return false;
				}
			}
		}
		return true;
	}

	public int[][] getActualGrid(){
		return Grid.deepCopyOfGrid(this.actualGrid);
	}
	
	public void setActualGrid(int row,int col,int value) {
		this.actualGrid[row][col] = value;		
	}
	
	public static boolean isGridFull(int[][] grid){
		for(int i = 0; i<9; i++){
			for(int j = 0; j<9; j++){
				if(grid[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public static ArrayList<int[]> getEmptyCells(int[][] grid){
		ArrayList<int[]> cells = new ArrayList<int[]>();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(grid[i][j] == 0){
					cells.add(new int[]{i,j});
				}
			}
		}
		return cells;
	}

	public static ArrayList<Integer> cellDomain(int x, int y, int[][] grid){
		ArrayList<Integer> domain = new ArrayList<Integer>();
		for(int i = 1; i < 10; i++){
			if(checkBox(x, y, i, grid) && checkRow(x, i, grid) && checkCol(y, i, grid)){
				domain.add(i);
			}
		}
		return domain;
	}

	public static ArrayList<int[]> getCellConstrains(int[][] grid, int x, int y){
		ArrayList<int[]> constrains = new ArrayList<int[]>();
		for(int i = 0; i < 9; i++){
			if(grid[x][i] == 0 && i != y){
				constrains.add(new int[]{x,i});
			}
		}
		for(int i = 0; i < 9; i++){
			if(grid[i][y] == 0 && i != x){
				constrains.add(new int[]{i,y});
			}
		}
		int row = (x / 3) * 3 ;
		int col = (y / 3) * 3 ;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if( grid[row+i][col+j] == 0 && row+i != x && col+j != y && !constrains.contains(new int[]{row+i, col+j})) {
					constrains.add(new int[]{row+i, col+j});
				}
			}
		}
		return constrains;
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
            	if (this.actualGrid[i][j] == 0){
					System.out.print(". ");
				}
				else{
					System.out.print(Integer.toString(this.actualGrid[i][j])+" ");
				}
        	}
		}
	}

	public static void displayGrid(int[][] grid) {
		System.out.println("\n");
		System.out.print("-----------------------------------------");
		for (int i = 0; i < 9; i++) {
            System.out.println();
            if(i%3==0)
                System.out.print("\n");
        	for (int j = 0; j < 9; j++) {
            	if (j % 3 == 0)
                	System.out.print(" ");
            	if (grid[i][j] == 0){
					System.out.print(". ");
				}
				else{
					System.out.print(Integer.toString(grid[i][j])+" ");
				}
        	}
		}
	}
	
	
		
}
