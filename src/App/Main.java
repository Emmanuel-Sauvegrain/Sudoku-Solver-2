package App;


public class Main {

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		
		long start = System.currentTimeMillis();
		Solver solver = new Solver(sudoku,9, true, true, true, true);
		boolean result = solver.solve();
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
