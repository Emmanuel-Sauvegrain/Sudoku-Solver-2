package App.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
 
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import App.Sudoku;

public class LCV {

    //Fonction qui permet de trier le domaine d'une cellule selon ses contraintes
    public static ArrayList<Integer> LCV_solve(ArrayList<Integer> domain, int x, int y, Sudoku sudoku){
        ArrayList<Integer> sortedDomain = new ArrayList<Integer>();
        HashMap<Integer,Integer> domainImpact = new HashMap<Integer,Integer>();
        for(int value : domain){
            domainImpact.put(value, 0);
        }
        //On parcours le domaine, et on observe comment chaque valeur affecte les autres cellules.
        ArrayList<int[]> contraints = sudoku.getCellConstraints(x, y);
        for(int[] c : contraints){
            ArrayList<Integer> cellDomain = sudoku.cellDomain(c[0], c[1]);
            for(int value : domain){
                if(cellDomain.contains(value)){
                    domainImpact.put(value, domainImpact.get(value) + 1);
                }
            }
        }
        //Moins une valeur affecte d'autres cellules, plus elle sera priorisée dans notre tri du domaine.
        HashMap<Integer, Integer> sortedDomainImpact = domainImpact
        .entrySet()
        .stream()
        .sorted(comparingByValue())
        .collect(
            toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
        for(int c : sortedDomainImpact.keySet()){
            sortedDomain.add(c);
        }
        return sortedDomain;
    }
}