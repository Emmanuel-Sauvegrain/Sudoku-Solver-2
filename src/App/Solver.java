package App;

import java.util.ArrayList;

import App.algorithms.*;

public class Solver {

	private Sudoku sudoku;
	private boolean mrv = false;
	private boolean dh = false;
	private boolean lcv = false;
	private boolean ac3 = false;
	
	public Solver(Sudoku sudoku,int gridSize, boolean mrv, boolean dh, boolean lcv, boolean ac3) {
		this.sudoku = sudoku;
		this.mrv = mrv;
		this.dh = dh;
		this.lcv = lcv;
		this.ac3 = ac3;
	}

	public boolean solve(){
		boolean result = false;
		if(this.sudoku.isGridFull()){
			return true;
		}
		ArrayList<int[]> emptyCells = this.sudoku.getEmptyCells();
		if(emptyCells.isEmpty()){
			return true;
		}
		//si MRv est activé :
		if(this.mrv){
			emptyCells = MRV.MRV_solve(emptyCells, this.sudoku);
		}
		if(this.dh){
			emptyCells = DH.DH_solve(emptyCells, this.sudoku);
		}
		int[] pos = emptyCells.get(0);
		ArrayList<Integer> domain = this.sudoku.cellDomain(pos[0], pos[1]);
		if(domain.isEmpty()){
			return false;
		}
		if(this.lcv){
			domain = LCV.LCV_solve(domain, pos[0], pos[1], this.sudoku);
		}
		for(int value : domain){
			this.sudoku.setValue(pos[0], pos[1], value);
			this.sudoku.displayGrid();
			if(!this.ac3){
				result = solve();
				if(result){
					return result;
				}
				this.sudoku.setValue(pos[0], pos[1], 0);
			}
			if(this.ac3 && AC3.AC3_solve(this.sudoku)){
				this.sudoku.setValue(pos[0], pos[1], 0);
			}
			else{
				result = solve();
				if(result){
					return result;
				}
				this.sudoku.setValue(pos[0], pos[1], 0);
			}
		}
		return result;
	}		
}
	
	
	

