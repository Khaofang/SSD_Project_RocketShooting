package model;

import ui.UI;

public class Rocket {

	private static Rocket instance;
	
	private BulletPool bp;
	private int y;
	
	private Rocket() {
		bp = new BulletPool();
		y = 0;
	}
	
	public static Rocket getInstance() {
		if (instance == null) {
			instance = new Rocket();
		}
		
		return instance;
	}
	
	public BulletPool getBulletPool() {
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
		bp.launch();
	}
	// TODO: implement methods that be required for game
	
}
