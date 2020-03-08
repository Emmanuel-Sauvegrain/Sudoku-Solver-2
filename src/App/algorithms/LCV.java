package App.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
 
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import App.Sudoku;

public class LCV {

    public static ArrayList<Integer> LCV_solve(ArrayList<Integer> domain, int x, int y, Sudoku sudoku){
        ArrayList<Integer> sortedDomain = new ArrayList<Integer>();
        HashMap<Integer,Integer> domainImpact = new HashMap<Integer,Integer>();
        for(int value : domain){
            domainImpact.put(value, 0);
        }
        ArrayList<int[]> contraints = sudoku.getCellConstraints(x, y);
        for(int[] c : contraints){
            ArrayList<Integer> cellDomain = sudoku.cellDomain(c[0], c[1]);
            for(int value : domain){
                if(cellDomain.contains(value)){
                    domainImpact.put(value, domainImpact.get(value) + 1);
                }
            }
        }
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