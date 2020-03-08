package App;

import java.util.ArrayList;

import App.algorithms.*;

public class Solver {

	private Sudoku sudoku;
	private boolean mrv = false;
	private boolean dh = false;
	private boolean lcv = false;
	private boolean ac3 = false;
	
	//Initialisation - Récupération du Sudoku (assignement) et des arguments
	public Solver(Sudoku sudoku,int gridSize, boolean mrv, boolean dh, boolean lcv, boolean ac3) {
		this.sudoku = sudoku;
		this.mrv = mrv;
		this.dh = dh;
		this.lcv = lcv;
		this.ac3 = ac3;
	}

	//Fonctino récursive de backtracking
	public boolean solve(){
		boolean result = false;
		//Condition d'arret : grille pleine
		if(this.sudoku.isGridFull()){
			return true;
		}
		//Condition d'arret : aucune case vide retournée par le Sudoku
		ArrayList<int[]> emptyCells = this.sudoku.getEmptyCells();
		if(emptyCells.isEmpty()){
			return true;
		}
		//si MRv est activé : (réduction des variables)
		if(this.mrv){
			emptyCells = MRV.MRV_solve(emptyCells, this.sudoku);
		}
		//si DH activé : (tri des variables)
		if(this.dh){
			emptyCells = DH.DH_solve(emptyCells, this.sudoku);
		}
		//On prend la premier variable de notre liste
		//Si MRV ou DH est activé, ce sera la variable la plus intéressante
		//Sinon, ce sera la plus proche dans la grille de 0;0
		int[] pos = emptyCells.get(0);
		//Récupération du domaine de la varaible choisie
		ArrayList<Integer> domain = this.sudoku.cellDomain(pos[0], pos[1]);
		//Condition de retour : le domaine d'une varaible est vide
		if(domain.isEmpty()){
			return false;
		}
		//Si LCV activé : (tri du domaine)
		if(this.lcv){
			domain = LCV.LCV_solve(domain, pos[0], pos[1], this.sudoku);
		}
		//On teste ensuite chaque valeur du domaine pour la varaible
		for(int value : domain){
			this.sudoku.setValue(pos[0], pos[1], value);
			this.sudoku.displayGrid();
			if(!this.ac3){
				//appel récursif
				result = solve();
				if(result){
					//retour si objectif atteint
					return result;
				}
				//remise de la variable à 0 si on a besoin de revenir en arrière
				this.sudoku.setValue(pos[0], pos[1], 0);
			}
			//Si AC3 activé : 
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
	
	
	

