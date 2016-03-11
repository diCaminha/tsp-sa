import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AppSA {
	

	private static Path generateInitialPath(CostMatrix matrix) {

		Path initialPath = new Path();
		for (int i = 0; i < matrix.getSize(); i++) {
			initialPath.getPath().add(i, i);
		}
		initialPath.shufflePath();
		
		return initialPath;

	}

	public static void main(String[] args) {

		int numberOfTemp = 15;
		int iterationsPerTemp = 30;
		int temperature = 50;
		
		int dEnergy;
		int e_candidate;
		double p;
		double r;
		boolean stop = false;
		
		CostMatrix matrix = new CostMatrix(40);
		matrix.fillMatrixRandomly();
		
		Path currentSolution = new Path();
		currentSolution = generateInitialPath(matrix);
		//System.out.println(currentSolution.calculateCostPath(matrix.getMatrix()));
		Path candidateSolution = new Path();


		// energy calculation of this current solution --> E(currentSolution)
		int e = currentSolution.calculateCostPath(matrix.getMatrix());

		int countIteration = 0;
		int countTemp = 0;

		while (!stop && temperature > 1) {
			if (countIteration > iterationsPerTemp) {

				temperature *= 0.75;
				countIteration = 0;
				countTemp++;

				if (countTemp > numberOfTemp) {
					stop = true;

				} else {

				}

			}
			if (countIteration <= iterationsPerTemp) {

				countIteration++;

				candidateSolution.setPath(currentSolution.getNeighbor());
				e_candidate = candidateSolution.calculateCostPath(matrix.getMatrix());
				System.out.println(e_candidate+ " " + e);
				
				dEnergy = e_candidate - e;
				if (dEnergy <= 0) {
					currentSolution = candidateSolution;
					e = e_candidate;
				} else {

					p = Math.exp(-(dEnergy / temperature));
					r = Math.random();

					if (r < p) {
						currentSolution = candidateSolution;
						e = e_candidate;
					}

				}

			}
		}

		
		System.out.println(e);
	}

}
