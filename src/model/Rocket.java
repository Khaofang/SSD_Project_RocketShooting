package model;

public class Rocket {

	private static Rocket instance;
	
	private BulletPool bp;
	private int y;
	
	private Rocket() {
		// TODO: set initial value of rocket
	}
	
	public static Rocket getInstance() {
		if (instance == null) {
			instance = new Rocket();
		}
		
		return instance;
	}
	
	public void move(int dY) {
		y += dY;
	}
	
	// TODO: implement methods that be required for game
	
}
