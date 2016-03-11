import java.util.Random;


public class CostMatrix {

	
	private int size;
	private int[][] matrix;
	
	
	
	public CostMatrix(int size) {
		this.size = size;
		this.matrix = new int[size][size];
	}

	public int[][] getMatrix(){
		return this.matrix;
	}

	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public void fillMatrixRandomly() {

		
		for (int i = 0; i < this.size; i++) {

			for (int j = 0; j < matrix.length - i; j++) {

				if (i == j) {
					matrix[i][j] = 0;

				} else {
					int val = new Random().nextInt(100) + 1;
					matrix[i][j] = val;
					matrix[j][i] = val;
				}
			}

		}
	}
}
