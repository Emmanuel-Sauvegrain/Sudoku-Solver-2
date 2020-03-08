package App.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
 
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import App.Sudoku;

public class DH {

    //Fonction de tri des cellules par degree heuristic.
    public static ArrayList<int[]> DH_solve(ArrayList<int[]> cells, Sudoku sudoku) {
        ArrayList<int[]> sortedCells = new ArrayList<int[]>();
        HashMap<int[], Integer> contraints = new HashMap<int[], Integer>();
        //Pour chaque cellule de la liste, on recupère les cellules qu'elle affecte lors d'un changement de valeur.
        //On sotcke cela sous dans une Map qui met en relation la cellule, et le nombre de cellules qu'elle affecte
        for (int[] c : cells) {
            contraints.put(c, sudoku.getCellConstraints(c[0], c[1]).size());
        }
        //On trie ensuite notre Map selon ses valeurs, pour retourner ensuite une liste de cellule triée selon leur impact
        HashMap<int[], Integer> sortedContraints = contraints
        .entrySet()
        .stream()
        .sorted(comparingByValue())
        .collect(
            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
        for(int[] c : sortedContraints.keySet()){
            sortedCells.add(c);
        }
        return sortedCells;
    }
}