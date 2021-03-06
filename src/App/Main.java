package App;


public class Main {

	public static void main(String[] args) {

		//Initialisation du Sudoku et de sa grille 
		Sudoku sudoku = new Sudoku();
		
		boolean mrv = false;
		boolean dh = false;
		boolean lcv = false;
		boolean ac3 = false;
		boolean changed = false;
		
		//Lecture des arguments en ligne de commande
		for(int i=0;i<args.length;i++) {
			
			if(args[i].contentEquals("mrv")){
				mrv=true;
			}
			
			if(args[i].contentEquals("dh")){
				dh=true;
			}
			
			if(args[i].contentEquals("lcv")){
				lcv=true;
			}
		
			if(args[i].contentEquals("ac3")){
				ac3=true;
			}
			
			if(args[i].indexOf("load")==0 && !changed) {
			
				String[] arrTemp = args[i].split("=",2);
				try {
					sudoku.loadGrid(arrTemp[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				changed = true;	
			}
			
			if(args[i].contentEquals("generate") && !changed) {
				Generator generator = new Generator();
				sudoku.changeGrid(generator.generateGrid());
				changed = true;
			}

		}
		//Initialisation et lancement du Solver, selon les arguments donnés, et affichage du résultat
		long start = System.currentTimeMillis();
		Solver solver = new Solver(sudoku,9, mrv, dh, lcv, ac3);
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
