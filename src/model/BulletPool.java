package model;

import java.util.ArrayList;
import java.util.List;

public class BulletPool {

	private List<Bullet> bullets;
	
	public BulletPool() {
		bullets = new ArrayList<Bullet>();
		for (int i = 0; i < 15; i++) {
			Bullet b = new Bullet();
			bullets.add(b);
		}
	}
	
	public void launch() {
		boolean launchNew = false;
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (b.isActive())
				b.shift(16);
			else if (!launchNew) {
				b.active();
			}
		}
	
	}
	
}
