public class UtilSA {

	private int quantPossibleVariationsInTemp;
	private int iterationsPerTemp;
	private double temperature;
	private int dEnergy;
	private int solution_energy;
	private int candidate_energy;
	private double p;
	private double r;
	boolean stopSimulatedAneallingProcess;
	
	int countIterationInCurrentTemp;
	int countVariationsInTemp;
	
	private Path currentSolution,candidateSolution;
	
	
	public UtilSA(int quantPossibleVariationsInTemp, int iterationsPerTemp,
			int initialTemp) {

		this.quantPossibleVariationsInTemp = quantPossibleVariationsInTemp;
		this.iterationsPerTemp = iterationsPerTemp;
		this.temperature = initialTemp;

		this.dEnergy = 0;
		this.candidate_energy = 0;
		this.p = 0.0;
		this.r = 0.0;
		
		candidateSolution = new Path();
		
		this.stopSimulatedAneallingProcess = false;
	}

	public void metropolisAlgorithm() {

		if (this.dEnergy <= 0) {
			setCurrentSolution(candidateSolution);
			setSolutionEnergy(candidate_energy);
			System.out.println("baixou");
		} else {

			p = Math.exp(-(dEnergy / temperature));
			r = Math.random();

			if (r < p) {
				setCurrentSolution(candidateSolution);
				setSolutionEnergy(candidate_energy);
				System.out.println("n baixou mas mudou");
			}

		}
	}


	public void execute_quenching(Path currentSolution, CostMatrix matrix) {

		countIterationInCurrentTemp = 0;
		countVariationsInTemp = 0;
		
		setCurrentSolution(currentSolution);
		
		// energy calculation of this current solution --> E(currentSolution)
		setSolutionEnergy(currentSolution.calculateCostPath(matrix.getMatrix()));

		while (!this.stopSimulatedAneallingProcess && this.temperature > 1) {

			if (countIterationInCurrentTemp > iterationsPerTemp) {
				// decrease temperature by a constant
				coolingTemperature();

				if (countVariationsInTemp > quantPossibleVariationsInTemp)
					stopSimulatedAneallingProcess = true;

			} else {

				countIterationInCurrentTemp++;
				
				//creating a new path candidate and calculating its energy
				candidateSolution.setPath(currentSolution.getNeighbor());
				setCandidateEnergy(candidateSolution.calculateCostPath(matrix.getMatrix()));

				// log
				System.out.println(getCandidate_energy() + " "
						+ getSolution_energy());

				calculate_dEnergy();

				metropolisAlgorithm();

			}
		}

	}

	private void coolingTemperature() {
		
		temperature *= 0.8;
		countVariationsInTemp++;
		countIterationInCurrentTemp = 0;
	}

	public void calculate_dEnergy() {

		this.dEnergy = this.candidate_energy - this.solution_energy;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getdEnergy() {
		return dEnergy;
	}

	public void setdEnergy(int dEnergy) {
		this.dEnergy = dEnergy;
	}

	public int getSolution_energy() {
		return solution_energy;
	}

	public void setSolutionEnergy(int solution_energy) {
		this.solution_energy = solution_energy;
	}

	public int getCandidate_energy() {
		return candidate_energy;
	}

	public void setCandidateEnergy(int candidate_energy) {
		this.candidate_energy = candidate_energy;
	}

	public void setCurrentSolution(Path currentSolution) {

		this.currentSolution = currentSolution;
	}
	
	public void setCandidateSolution(Path candidateSolution){
		this.candidateSolution = candidateSolution;
	}
}
