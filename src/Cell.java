import java.util.ArrayList;


public class Cell {
	private boolean visited;
	private int number;
	private ArrayList<Integer> connections;
	
	Cell(int number) {
		this.number = number;
		this.visited = false;
		this.connections = new ArrayList<Integer>();
	}
	
	public void addConnection(int number) {
		this.connections.add(number);
	}
	
	public boolean isConnected(int number){
		return this.connections.contains(number);
	}
	
	public void visit() {
		this.visited = true;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public int getNumber(){
		return this.number;
	}
}
