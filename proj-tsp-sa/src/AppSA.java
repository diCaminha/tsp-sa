import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AppSA {
	

	public static void main(String[] args) {

		
		CostMatrix matrix = new CostMatrix(40);
		matrix.fillMatrixRandomly();
		
		Path currentSolution = new Path();
		currentSolution.generateInitialPath(matrix);
		
		//System.out.println(currentSolution.calculateCostPath(matrix.getMatrix()));
		Path candidateSolution = new Path();


		UtilSA sa = new UtilSA(15, 30, 50);
		
		// energy calculation of this current solution --> E(currentSolution)
		sa.setE_solution(currentSolution.calculateCostPath(matrix.getMatrix()));
		
		
		int countIteration = 0;
		int countTemp = 0;

		while (!sa.stop && sa.getTemperature() > 1) {
			if (countIteration > sa.getIterationsPerTemp()) {

				sa.setTemperature(sa.getTemperature() * 0.75); 
				countIteration = 0;
				countTemp++;

				if (countTemp > sa.getNumberOfTemp()) {
					sa.stop = true;

				} else {

				}

			}
			if (countIteration <= sa.getIterationsPerTemp()) {

				countIteration++;

				candidateSolution.setPath(currentSolution.getNeighbor());
				
				sa.setE_candidate(candidateSolution.calculateCostPath(matrix.getMatrix()));
				
				System.out.println(sa.getE_candidate()+ " " + sa.getE_solution());
				
				sa.calculate_dEnergy();
				
				sa.metropolisAlgorithm(currentSolution,candidateSolution);

			}
		}

		
		System.out.println(sa.getE_solution());
	}

}
