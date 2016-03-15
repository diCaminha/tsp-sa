import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AppSA {

	public static int numberOfCities = 100;
	public static int quantPossibleChangesInTemp = 18; // 10-20
	public static int iterationsPerTemp = 90; // 20-100
	public static int initialTemp = 50;

	public static void main(String[] args) {

		// inicializing and filling the matrix used in the program with the
		// number of cities
		CostMatrix matrix = new CostMatrix(numberOfCities);
		matrix.fillMatrixRandomly();

		// creating a random path through the cities in matrix, and initilizing
		// a candidate Solution
		Path currentSolution = new Path();
		currentSolution.generateInitialPath(matrix);

		UtilSA sa = new UtilSA(quantPossibleChangesInTemp, iterationsPerTemp, initialTemp);
		
		
		sa.execute_quenching(currentSolution, matrix);

		int solution = sa.getSolution_energy();
		
		System.out.println(solution);
	}

}
