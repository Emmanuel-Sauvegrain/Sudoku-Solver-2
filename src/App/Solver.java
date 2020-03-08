package App;

import java.util.ArrayList;

public class Solver {

	private int gridSize;
	private Grid grid;
	
	public Solver(Grid grid,int gridSize) {
		this.gridSize = gridSize;
		this.grid = grid;
		try {
			//solveCell(0,0);
			solve(this.grid.getActualGrid());
			
		}catch( Exception e) {	
		}
		//solverMRV();
		//solverDegreeH();	
		
	}

	private void solve(int[][] grid) throws Exception {
		if(Grid.isGridFull(grid)){
			System.out.println();
			System.out.println("Solution found");
			throw new Exception();
		}
		ArrayList<int[]> emptyCells = Grid.getEmptyCells(grid);
		if(emptyCells.isEmpty()){
			return;
		}
		//si aucun parametre activé :
		int[] pos = emptyCells.get(0);
		ArrayList<Integer> domain = Grid.cellDomain(pos[0], pos[1], grid);
		for(int i : domain){
			System.out.println(i);
		}
		if(domain.isEmpty()){
			return;
		}
		for(int value : domain){
			grid[pos[0]][pos[1]] = value;
			Grid.displayGrid(grid);
			solve(Grid.deepCopyOfGrid(grid));
		}
	}
	
	
	
	/*private void solveCell(int row, int col) throws Exception {
		if(row > this.gridSize-1) {
			System.out.println("	----- Solution trouvée ----");
			throw new Exception() ;	
		}
		// If the cell is not empty
		if( this.grid.actualGrid[row][col] != 0){
			changeCell(row,col);
			
		}else {
			// Find a valid number for the cell
			   for(int tryNumber=1; tryNumber<10;tryNumber++) {
				   if( grid.checkRow(row,tryNumber) && grid.checkCol(col,tryNumber) && grid.checkBox(row,col,tryNumber)) {
						this.grid.setActualGrid(row, col, tryNumber);
						this.grid.displayActualGrid();
						changeCell(row,col);
				   }
			   }
			   // No valid Number was found
			   //checkGrid[row][col] = 0;
			   //updateGrid();   
		   }
		}
		
		
	
	private void changeCell(int row, int col) throws Exception {
		if(col<gridSize-1) {
			solveCell(row,col+1);
		}else {
			solveCell(row+1,0);
		}
	}*/
	
	/*private boolean solverMRV() {
		
		int row = 0;
		int col = 0;
		int maxNumber = 10;
		int numberPossibility;
		
		for(int i=0;i<gridSize;i++) {
			for(int j=0;j<gridSize;j++) {
				
				if(checkGrid[i][j] == 0) {
					numberPossibility = getPossibleValues(i,j).size();
					if(numberPossibility < maxNumber) {
						maxNumber = numberPossibility;
						row = i;
						col = j;
					}
				}
			}
		}
		
		if(maxNumber==10) {
			return true;
		}
		for(int possibleNumber:getPossibleValues(row,col)) {
			checkGrid[row][col]=possibleNumber;
			updateGrid();
			if(solverMRV()) {
				return true;
			} else {
				checkGrid[row][col]=0;
				updateGrid();
			}
		}
		checkGrid[row][col]=0;
		updateGrid();
		return false;
	}
	
private boolean solverDegreeH() {
		
		int row = 0;
		int col = 0;
		int minNumber = 0;
		int numberPossibility;
		
		for(int i=0;i<gridSize;i++) {
			for(int j=0;j<gridSize;j++) {
				
				if(checkGrid[i][j] == 0) {
					numberPossibility = getPossibleValues(i,j).size();
					if(numberPossibility > minNumber) {
						minNumber = numberPossibility;
						row = i;
						col = j;
					}
				}
			}
		}
		
		if(minNumber==0) {
			return true;
		}
		for(int possibleNumber:getPossibleValues(row,col)) {
			checkGrid[row][col]=possibleNumber;
			updateGrid();
			if(solverMRV()) {
				return true;
			} else {
				checkGrid[row][col]=0;
				updateGrid();
			}
		}
		checkGrid[row][col]=0;
		updateGrid();
		return false;
	}
	
	private LinkedList<Integer> getPossibleValues(int row, int col) {
		LinkedList<Integer> list = new LinkedList<Integer>();
			
			for(int number=1;number<=9;number++) {
				if(grid.checkRow(row,number,checkGrid) && grid.checkCol(col,number,checkGrid) && grid.checkBox(row,col,number,checkGrid)){
				
					list.add(number);
					
				}
			
			}
			return list;
		
	}*/
	
	
		
		
		
}
	
	
	

