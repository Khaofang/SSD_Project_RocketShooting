package model;

import java.util.ArrayList;
import java.util.List;

public class RocketBulletPool {

	private static RocketBulletPool instance = null;
	
	private List<Bullet> bullets;
	
	private RocketBulletPool() {
		bullets = new ArrayList<Bullet>();
		for (int i = 0; i < 15; i++) {
			Bullet b = new Bullet();
			bullets.add(b);
		}
	}
	
	public static RocketBulletPool getInstance() {
		if (instance == null)
			instance = new RocketBulletPool();
		
		return instance;
	}
	
	public List<Bullet> getBullets() {
		return bullets;
	}
	
	public void launch(int y) {
		boolean launchNew = false;
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (b.isActive()) {
				b.shift(64);
				if (b.isHit())
					b.deactive();
			} else if (!launchNew) {
				b.active(y + 28);
				launchNew = true;
			}
		}
	
	}
	
}
