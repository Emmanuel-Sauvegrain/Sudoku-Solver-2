package App.algorithms;

import java.util.ArrayList;

import App.Sudoku;

public class MRV {

    //MRV fonction pour réduire les variables sur lesquelles on va travailler selon la taille de leur domaine
    public static ArrayList<int[]> MRV_solve(ArrayList<int[]> cells, Sudoku sudoku){
        ArrayList<int[]> newCells = new ArrayList<int[]>();
        int minDomian = 999;
        //On parcours la liste de cellules données, on cherche celles avecl a plus petite taille de domaine.
        //Si on recontre une tailel de domaine plus petite que notre minimum, celui ci change, on vide la liste de retour
        //et on stocke les nouvelles cellules que l'ont veut retourner.
        for(int[] pos : cells){
            int domainSize = sudoku.cellDomain(pos[0], pos[1]).size();
            if(domainSize < minDomian){
                minDomian = domainSize;
                newCells.clear();
                newCells.add(pos);
            }
            else if(domainSize == minDomian){
                newCells.add(pos);
            }
        }
        return newCells;
    }
}