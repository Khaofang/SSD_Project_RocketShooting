<<<<<<< HEAD
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
		// TODO: make bullet active
	}
	
}
=======
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
		// TODO: make bullet active
	}
	
}
>>>>>>> 8de83d3f3c43aee1e6d2ab21d3586a2d2b1ab4bd
