package task2;

public class Score {
	private int points;
	
	public Score() {
		this.points = 0;
	}
	
	public void addPoints () {
		this.points ++;
	}
	
	public void resetScore() {
		this.points = 0;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "" + this.getPoints();
	}
	
	
}
