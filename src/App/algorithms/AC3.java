package App.algorithms;

import App.Sudoku;

import java.util.ArrayList;
import java.util.Queue;

public class AC3 {

    //Fonction qui va nous donner la faisabilité du Sudoku selon la disposition donnée
    //Si le domaine d'une des cellules sans valeur tombe à 0, la configuration actuelle ne permet pas de résolution.
    public static boolean AC3_solve(Sudoku sudoku){
        //On s'assure que tout les domaines soient bons et à jour
        sudoku.updateDomains();
        //Récupération des arcs de contraintes
        Queue<Arc> arcs = sudoku.getArcs();
        while(!arcs.isEmpty()){
            Arc a = arcs.remove();
            //On observe si des valeurs sont à retirer du domaine
            if(removeInconsistentValues(sudoku, a)){
                //Si apres retrait, le domaine devient vide, la configuration actuelle n'est pas valable
                if(sudoku.domains.get(Integer.toString(a.x[0])+Integer.toString(a.x[1])).size() == 0){
                    return true;
                }
                //Sinon, on ajoute les contraintes de la cellule sur laquelle on vient de travailler 
                //pour retirer d'autres valeurs qui pourraient maintenant etre inconsistantes
                ArrayList<int[]> cons = sudoku.getCellConstraints(a.x[0], a.x[1]);
                for(int[] c : cons){
                    arcs.add(new Arc(c, a.x));
                }
            }
        }
        return false;
    }

    //Cela nous permet de retirer toutes les valeurs inconsistantes dans le domaine d'une variable donnée
    //selon sa contraintes avec une variable
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

    //Si une valeur et un domaine donnés sont valables pour nos contraintes (ie la valeur n'empeche pas toutes les autres valeurs du domaine)
    private static boolean satisfy(int value, ArrayList<Integer> domain){
        for(int d: domain){
            if(d != value){
                return true;
            }
        }
        return false;
    }

    //Deep cpoy d'une ArrayList pour permettre de la modifier sans briser nos itérations
    private static ArrayList<Integer> arrayListDeepCopy(ArrayList<Integer> list){
        ArrayList<Integer> newList = new ArrayList<Integer>();
		for(int i : list){
			newList.add(i);
        }
        return newList;
    }
}