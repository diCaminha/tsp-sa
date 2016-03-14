public class UtilSA {

	private int numberOfTemp;
	private int iterationsPerTemp;
	private double temperature;
	private int dEnergy;
	private int e_solution;
	private int e_candidate;
	private double p;
	private double r;
	boolean stop;

	public UtilSA(int numberOfTemp, int iterationsPerTemp, int initialTemp) {

		this.numberOfTemp = numberOfTemp;
		this.iterationsPerTemp = iterationsPerTemp;
		this.temperature = initialTemp;

		this.dEnergy = 0;
		this.e_candidate = 0;
		this.p = 0.0;
		this.r = 0.0;

		this.stop = false;
	}

	public void calculate_dEnergy() {

		this.dEnergy = this.e_candidate - this.e_solution;
	}

	public void metropolisAlgorithm(Path currentSolution, Path candidateSolution) {

		if (this.dEnergy <= 0) {
			currentSolution = candidateSolution;
			e_solution = e_candidate;
		} else {

			p = Math.exp(-(dEnergy / temperature));
			r = Math.random();

			if (r < p) {
				currentSolution = candidateSolution;
				e_solution = e_candidate;
			}

		}
	}

	public int getNumberOfTemp() {
		return numberOfTemp;
	}

	public void setNumberOfTemp(int numberOfTemp) {
		this.numberOfTemp = numberOfTemp;
	}

	public int getIterationsPerTemp() {
		return iterationsPerTemp;
	}

	public void setIterationsPerTemp(int iterationsPerTemp) {
		this.iterationsPerTemp = iterationsPerTemp;
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

	public int getE_solution() {
		return e_candidate;
	}

	public void setE_solution(int e_solution) {
		this.e_solution = e_solution;
	}

	public int getE_candidate() {
		return e_candidate;
	}

	public void setE_candidate(int e_candidate) {
		this.e_candidate = e_candidate;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
