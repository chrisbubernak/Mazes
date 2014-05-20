import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Maze {
	private int n;
	private int size;
	private int goal;
	private int current;
	private Cell [] cells;

	//create a square maze of width n
	//start the user at 0 and put the goal at (n^2)-1
	public Maze(int n) {
		this. n = n;
		this.size = n*n;
		this.goal = this.size-1;
		this.current = 0;
		this.cells = new Cell[size];
		for (int i = 0; i < size; i++) {
			this.cells[i] = new Cell(i);
		}
		DFS(goal);
	}
	
	public Cell getCell(int number) {
		return this.cells[number];
	}
	
	public int getN() {
		return this.n;
	}
	
	public int getCurrent() {
		return this.current;
	}
	
	public int getGoal() {
		return this.goal;
	}
	
	public void setCurrent(int number) {
		this.current = number;
	}
	
	private void DFS(int cur) {
		this.cells[cur].visit();
		ArrayList<Integer> neighbors = neighbors(cur);
		long seed = System.nanoTime();
		Collections.shuffle(neighbors, new Random(seed));
		
		for (int i = 0; i <neighbors.size(); i++ ) {
			int neighbor = neighbors.get(i);
			if (!this.cells[neighbor].isVisited()) {
				this.cells[cur].addConnection(neighbor);
				this.cells[neighbor].addConnection(this.cells[cur].getNumber());
				DFS(neighbor);
			}
		}
	}

	private ArrayList<Integer> neighbors(int cur) {
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		
		//add the cell above us if we arean't in the top row
		if(cur>=n) {
			neighbors.add(cur-n);
		}
		//add the cell to our left if we arean't in the first col
		if(cur%n!=0) {
			neighbors.add(cur-1);
		}
		//add the cell to our right if we arean't in the last col
		if((cur+1)%n!=0) {
			neighbors.add(cur+1);
		}
		
		//add the cell below us if we arean't in the bottom row
		if (cur < (this.size-n)) {
			neighbors.add(cur+n);
		}
		
		return neighbors;
	}

}
