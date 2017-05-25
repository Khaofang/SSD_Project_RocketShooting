package model;

public class Enemy implements Opponent {

	//private BulletPool bp;
	private boolean active;
	private boolean hided;
	private int x;
	private int y;
	
	public Enemy() {
		active = false;
		hided = false;
		x = 0;
		y = 0;
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	
	@Override
	public void hide() {
		hided = true;
	}
	
	@Override
	public void active() {
		int i = (int) (Math.random() * 6);
		x = 840;
		y = 48 + (i * 64);
		active = true;
	}
	
	@Override
	public void deactive() {
		active = false;
		hided = false;
	}
	
	@Override
	public boolean inMap() {
		return x + 64 >= 0 ;
	}
	
	@Override
	public void interrupt() {
		// TODO: make enemy launch bullets later
	}
	
	@Override
	public boolean isActive() {
		return active;
	}
	
	@Override
	public boolean isHided() {
		return hided;
	}

	@Override
	public void move() {
		x -= 5;
	}

}
