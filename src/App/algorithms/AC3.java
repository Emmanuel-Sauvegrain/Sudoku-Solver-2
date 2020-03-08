package App.algorithms;

import App.Sudoku;

import java.util.ArrayList;
import java.util.Queue;

public class AC3 {

    public static boolean AC3_solve(Sudoku sudoku){
        //On s'assure que tout les domaines soient bons et à jour
        sudoku.updateDomains();
        Queue<Arc> arcs = sudoku.getArcs();
        while(!arcs.isEmpty()){
            Arc a = arcs.remove();
            if(removeInconsistentValues(sudoku, a)){
                if(sudoku.domains.get(Integer.toString(a.x[0])+Integer.toString(a.x[1])).size() == 0){
                    return true;
                }
                ArrayList<int[]> cons = sudoku.getCellConstraints(a.x[0], a.x[1]);
                for(int[] c : cons){
                    arcs.add(new Arc(c, a.x));
                }
            }
        }
        return false;
    }

    private static boolean removeInconsistentValues(Sudoku sudoku, Arc arc){
        boolean removed = false;
        ArrayList<Integer> temp = arrayListDeepCopy(sudoku.domains.get(Integer.toString(arc.x[0])+Integer.toString(arc.x[1])));
        for(int a : sudoku.domains.get(Integer.toString(arc.x[0])+Integer.toString(arc.x[1]))){
            if(!satisfy(a, sudoku.domains.get(Integer.toString(arc.y[0])+Integer.toString(arc.y[1])))){
                temp.remove(
					temp.indexOf(a)
				);
                removed = true;
            }
            sudoku.domains.put(Integer.toString(arc.x[0])+Integer.toString(arc.x[1]), temp);
        }
        return removed;
    }

    private static boolean satisfy(int value, ArrayList<Integer> domain){
        for(int d: domain){
            if(d != value){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Integer> arrayListDeepCopy(ArrayList<Integer> list){
        ArrayList<Integer> newList = new ArrayList<Integer>();
		for(int i : list){
			newList.add(i);
        }
        return newList;
    }
}