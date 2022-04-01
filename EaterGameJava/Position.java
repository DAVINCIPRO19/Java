public class Position {
	private int x;
	private int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	void setX(int x) {
		this.x = x;
	}
	void setY(int y) {
		this.y = y;
	}
	boolean equals(Position position) {
		if(this.x == position.getX() && this.y == position.getY()) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}