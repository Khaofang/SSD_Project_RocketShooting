package model;

import ui.UI;

public class Rocket {

	private static Rocket instance;
	
	private RocketBulletPool bp;
	private int y;
	
	private Rocket() {
		bp = RocketBulletPool.getInstance();
		y = 0;
	}
	
	public static Rocket getInstance() {
		if (instance == null) {
			instance = new Rocket();
		}
		
		return instance;
	}
	
	public RocketBulletPool getBulletPool() {
		return bp;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int dY) {
		y += dY;
	}
	
	public void shoot() {
		bp.launch(y);
	}
	// TODO: implement methods that be required for game
	
}
