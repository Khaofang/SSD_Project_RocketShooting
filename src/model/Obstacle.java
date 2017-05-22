package model;

public class Obstacle implements Opponent {

	private BulletPool bp;
	private boolean active;
	private int x;
	private int y;
	
	public Obstacle() {
		active = false;
		x = 0;
		y = 0;
	}
	
	@Override
	public void setStart() {
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
		return x + 64 < 0 ;
	}

	@Override
	public void shift() {
		x -= 64;
		
	}

	@Override
	public void deactive() {
		active = false;
	}

}
