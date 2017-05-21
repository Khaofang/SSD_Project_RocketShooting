<<<<<<< HEAD
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
	
	public void setY(int y) {
		this.y = y;
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
=======
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
	
	public void setY(int y) {
		this.y = y;
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
>>>>>>> 8de83d3f3c43aee1e6d2ab21d3586a2d2b1ab4bd
