package model;

public class Bullet {

	private boolean active;
	private int x;
	private int y;
	
	public Bullet() {
		active = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void active() {
		active = true;
		x = 60;
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
