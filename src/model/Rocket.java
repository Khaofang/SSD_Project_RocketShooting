<<<<<<< HEAD
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
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int dY) {
		y += dY;
	}
	
	// TODO: implement methods that be required for game
	
}
=======
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
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int dY) {
		y += dY;
	}
	
	// TODO: implement methods that be required for game
	
}
>>>>>>> 8de83d3f3c43aee1e6d2ab21d3586a2d2b1ab4bd
