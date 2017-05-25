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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void active(int y) {
		active = true;
		x = 80;
		this.y = y;
	}

	public void deactive() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}

	public boolean inMap() {
		return x >= 840 || x <= 0;
	}

	public void shift(int dX) {
		x += dX;
	}

}
