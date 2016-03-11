import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {

	private List<Integer> path;
	private int cost;
	private int changerPosition = 0;//variable used in alteratepath method

	public Path() {

		this.path = new ArrayList<Integer>();
		this.cost = 0;
	}
	
	public List<Integer> getPath(){
		return path;
	}
	
	public int calculateCostPath(int[][] matrizCusto) {
		this.cost = 0;
		for (int i = 0; i < this.path.size() - 1; i++) {

			cost += matrizCusto[this.path.get(i)][this.path.get(i + 1)];
		}

		return cost;
	}

	public List<Integer> getNeighbor() {

		List<Integer> neighbor = new ArrayList<>();
		alteratePath(neighbor, this.path);

		return neighbor;
	}

	public void alteratePath(List<Integer> a, List<Integer> b) {
		
		a.add(0, b.get(b.size()-1));
		a.add(a.size()-1,b.get(0));
		
		if (changerPosition > b.size() - 2)
			changerPosition = 1;

		for (int i = 1; i < b.size() - 1; i++) {
			if (i == changerPosition) {
				a.add(i, b.get(i + 1));

			} else if (i == changerPosition + 1) {
				a.add(i, b.get(i - 1));
			} else {
				a.add(i, b.get(i));

			}

		}
		changerPosition++;
	}

	public void shufflePath() {

		Collections.shuffle(this.path);
	}

	public void setPath(List<Integer> path) {

		this.path = path;
	}
}
