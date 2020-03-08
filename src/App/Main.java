package App;

import java.util.ArrayList;

import App.algorithms.*;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid();
		
		long start = System.currentTimeMillis();
		Solver solver = new Solver(grid,9, true, true, true, false);
		boolean result = solver.solve(grid.getActualGrid());
		long end = System.currentTimeMillis();
		if(result){
			System.out.println();
			System.out.println("Successful resolution in "+ Long.toString(end - start) + " ms");
		}
		else{
			System.out.println();
			System.out.println("Resolution failed");
		}

	}

}
