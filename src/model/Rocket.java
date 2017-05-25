package model;

import ui.UI;

public class Rocket {

	private static Rocket instance;

	private RocketBulletPool bulletpool;
	private int y;
	private int x;

	private Rocket() {
		bulletpool = RocketBulletPool.getInstance();
		y = 0;
	}

	public static Rocket getInstance() {
		if (instance == null) {
			instance = new Rocket();
		}

		return instance;
	}

	public RocketBulletPool getBulletPool() {
		return bulletpool;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move(int dY) {
		y += dY;
	}

	public void shoot() {
		bulletpool.launch(y);
	}
	
	public void reset() {
		y = 240;
	}

}
