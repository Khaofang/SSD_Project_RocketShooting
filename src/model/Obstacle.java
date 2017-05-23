package model;

public class Obstacle implements Opponent {

	private boolean active;
	private int x;
	private int y;
	
	public Obstacle() {
		active = false;
		x = 0;
		y = 0;
	}
	
	@Override
	public void active() {
		int i = (int) (Math.random() * 6);
		x = 840;
		y = 48 + (i * 64);
		active = true;
	}
	
	@Override
	public boolean isActive() {
		return active;
	}
	
	@Override
	public void interrupt() {
	}

	@Override
	public boolean inMap() {
		return x + 64 >= 0 ;
	}

	@Override
	public void move() {
		x -= 5;
	}

	@Override
	public void deactive() {
		active = false;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
