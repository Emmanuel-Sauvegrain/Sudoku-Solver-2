package App;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import App.algorithms.Arc;


public class Sudoku {

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
	private int[][] grid = {
			{0,3,1, 0,0,2, 0,0,8},
			{6,0,0, 0,8,0, 0,0,0},
			{0,0,0, 0,0,0, 0,3,1},
			{7,5,0, 0,0,0, 0,6,9},
			{0,9,0, 0,1,0, 0,2,0},
			{0,0,8, 0,3,6, 0,0,0},
			{9,0,0, 0,0,5, 0,0,0},
			{0,7,0, 2,0,0, 0,0,0},
			{1,0,0, 6,0,0, 4,0,0} };
	public HashMap<String, ArrayList<Integer>> domains;

	public Sudoku(){
		this.domains = new HashMap<String, ArrayList<Integer>>();
		ArrayList<int[]> cells = this.getEmptyCells();
		for(int[] c : cells){
			this.domains.put(Integer.toString(c[0])+Integer.toString(c[1]) , this.cellDomain(c[0], c[1]));
		}
	}


	public void loadGrid(String filename) throws Exception {
		
		int i;
		int j;
		int number;
		Scanner file = new Scanner(new FileReader(filename));
		file.useDelimiter("");
		
		while(file.hasNextInt()) {

			for(i=0;i<9;i++) {
				for(j=0;j<9;j++) {
					number = file.nextInt();
					this.grid[i][j] = number;
				}
			}
		}
		file.close();
	}
	
	
	public boolean checkRow(int row,int number) {
		for(int i=0; i<9; i++) {
			if(number == this.grid[row][i]) {
				return false;
			}			
		}
		return true;
	}
	
	public boolean checkCol(int col,int number) {
		for(int i=0; i<9; i++) {
			if(number == this.grid[i][col]) {
				return false;
			}			
		}
		return true;
	}
	
	
	public boolean checkBox( int row, int col, int number) {
		row = (row / 3) * 3 ;
		col = (col / 3) * 3 ;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if( this.grid[row+i][col+j] == number) {
					return false;
				}
			}
		}
		return true;
	}

	public int[][] getGrid(){
		return this.grid;
	}
	
	public void setValue(int row,int col,int value) {
		this.grid[row][col] = value;		
	}
	
	public boolean isGridFull(){
		for(int i = 0; i<9; i++){
			for(int j = 0; j<9; j++){
				if(this.grid[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public ArrayList<int[]> getEmptyCells(){
		ArrayList<int[]> cells = new ArrayList<int[]>();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(this.grid[i][j] == 0){
					cells.add(new int[]{i,j});
				}
			}
		}
		return cells;
	}

	public ArrayList<Integer> cellDomain(int x, int y){
		ArrayList<Integer> domain = new ArrayList<Integer>();
		for(int i = 1; i < 10; i++){
			if(this.checkBox(x, y, i) && this.checkRow(x, i) && this.checkCol(y, i)){
				domain.add(i);
			}
		}
		//Update du domaine de la case dans la HashMap générale
		this.domains.put(Integer.toString(x)+Integer.toString(y), domain);
		return domain;
	}

	public ArrayList<int[]> getCellConstraints(int x, int y){
		ArrayList<int[]> constraints = new ArrayList<int[]>();
		for(int i = 0; i < 9; i++){
			if(this.grid[x][i] == 0 && i != y){
				constraints.add(new int[]{x,i});
			}
		}
		for(int i = 0; i < 9; i++){
			if(this.grid[i][y] == 0 && i != x){
				constraints.add(new int[]{i,y});
			}
		}
		int row = (x / 3) * 3 ;
		int col = (y / 3) * 3 ;
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if( this.grid[row+i][col+j] == 0 && row+i != x && col+j != y && !constraints.contains(new int[]{row+i, col+j})) {
					constraints.add(new int[]{row+i, col+j});
				}
			}
		}
		return constraints;
	}

	public Queue<Arc> getArcs() {
		Queue<Arc> arcs = new LinkedList<Arc>();
		ArrayList<int[]> cells = this.getEmptyCells();
		for(int[] c : cells){
			ArrayList<int[]> constraints = this.getCellConstraints(c[0], c[1]);
			for(int[] i : constraints){
				arcs.add(new Arc(c, i));
			}
		}
		return arcs;
	}

	public void updateDomains(){
		this.domains.clear();
		ArrayList<int[]> cells = this.getEmptyCells();
		for(int[] c : cells){
			this.domains.put(Integer.toString(c[0])+Integer.toString(c[1]), this.cellDomain(c[0], c[1]));
		}
	}
	
	
	public void displayGrid() {
		System.out.print("-----------------------------------------");
		for (int i = 0; i < 9; i++) {
            System.out.println();
            if(i%3==0)
                System.out.print("\n");
        	for (int j = 0; j < 9; j++) {
            	if (j % 3 == 0)
                	System.out.print(" ");
            	if (this.grid[i][j] == 0){
					System.out.print(". ");
				}
				else{
					System.out.print(Integer.toString(this.grid[i][j])+" ");
				}
        	}
		}
		System.out.println("\n");
	}
		
}
