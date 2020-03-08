package App;

import java.util.ArrayList;

import App.algorithms.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Grid grid = new Grid();
			
		
		long start = System.currentTimeMillis();
		try {
			Solver solver = new Solver(grid,9, true, true, true, false);
		}catch( Exception e) {
			
		}
		long end = System.currentTimeMillis();
		System.out.println();
		System.out.println("Sudoku résolu en " + Long.toString(end - start) + " millisecondes");

	}

}
