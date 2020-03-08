package App.algorithms;

import java.util.ArrayList;

import App.Grid;

public class MRV {

    public static ArrayList<int[]> MRV_solve(ArrayList<int[]> cells, int[][] grid){
        ArrayList<int[]> newCells = new ArrayList<int[]>();
        int minDomian = 999;
        for(int[] pos : cells){
            int domainSize = Grid.cellDomain(pos[0], pos[1], grid).size();
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