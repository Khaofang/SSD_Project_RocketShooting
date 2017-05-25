package model;

public abstract class Opponent {

	protected boolean active;
	protected boolean hided;
	protected int x;
	protected int y;
	
	protected Opponent() {
		active = false;
		hided = false;
		x = 1000;
		y = 1000;
	}
	
	public abstract void hide();
	public abstract void interrupt();
	public abstract boolean isObstacle();
	public abstract void move();
	
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	public final void active() {
		int i = (int) (Math.random() * 6);
		x = 840;
		y = 48 + (i * 64);
		active = true;
	}
	
	public final void deactive() {
		active = false;
		hided = false;
		x = 1000;
		y = 1000;
	}
	
	public final boolean inMap() {
		return x + 64 >= 0 ;
	}
	
	public final boolean isActive() {
		return active;
	}
	
	public final boolean isHided() {
		return hided;
	}
}