package tp03;

public class Cellule {

	private int x;
	private int y;
	
	public Cellule(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean equals(Cellule c) {
		return this.x == c.x && this.y == c.y;
	}
	
}