package App;


public class Main {

	public static void main(String[] args) {

		//Initialisation du Sudoku et de sa grille 
		Sudoku sudoku = new Sudoku();
		
		boolean mrv;
		boolean dh;
		boolean lcv;
		boolean ac3;
		
		//Lecture des arguments en ligne de commande
		for(int i=0;i<args.length;i++) {
			
			if(args[i].contentEquals("mrv")){
				mrv=true;
				System.out.println("MRV");
			}
			
			if(args[i].contentEquals("dh")){
				dh=true;
				System.out.println("DH");
			}
			
			if(args[i].contentEquals("lcv")){
				lcv=true;
				System.out.println("LCV");
			}
		
			if(args[i].contentEquals("ac3")){
				ac3=true;
				System.out.println("AC3");
			}

		}
		//Initialisation et lancement du Solver, selon les arguments donnés, et affichage du résultat
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
