package model;

public class Bullet {

	private boolean active;
	private int x;
	private int y;
	
	public Bullet(int x, int y) {
		active = false;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void active() {
		active = true;
	}
	
	public void deactive() {
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void shift(int dX) {
		x += dX;
	}
	
}
