package App;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Grid grid = new Grid();
			
		
		long start = System.currentTimeMillis();
		try {
			Solver solver = new Solver(grid,9);
		}catch( Exception e) {
			
		}
		long end = System.currentTimeMillis();
		System.out.println("Soddoku exécuté en " + Long.toString(end - start) + " millisecondes");
		

	}

}
