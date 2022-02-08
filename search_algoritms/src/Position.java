/**
 * 
 * A class to organise (x, y) pairs
 * 
 * @author Kosmas S.
 *
 */
public class Position {
	private int x, y;
	private int costFromStart=0;
	private Position parent = null; 
	private String move = null;
	
	public String getMove() {
		return move;
	}


	public void setMove(String move) {
		this.move = move;
	}


	public Position getParent() {
		return parent;
	}


	public void setParent(Position parent) {
		this.parent = parent;
	}


	public int getCostFromStart() {
		return costFromStart;
	}
	

	public void setCostFromStart(int costFromStart) {
		this.costFromStart = costFromStart;
	}
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public int heuristic(Position p , Position goal ) {
		int toReturn;
		toReturn =	goal.getX()-p.getX()+ goal.getY()-p.getY();
		return toReturn;
	}


	
}