package model;

import java.util.ArrayList;
import java.util.List;

public class EnemyBulletPool {

    private static EnemyBulletPool[] instance = new EnemyBulletPool[6];

    private List<Bullet> bullets;

    private EnemyBulletPool() {
        bullets = new ArrayList<Bullet>();
        for (int i = 0; i < 20; i++) {
            Bullet b = new Bullet();
            bullets.add(b);
        }
    }

    public static EnemyBulletPool[] getInstance() {
        if (instance == null) {
            for (int i = 0; i < 6; i++)
            	instance[0] = new EnemyBulletPool();
        }
        return instance;
    }
    
    public void launch(int x, int y) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (!b.isActive()) {
				b.active(y + 20);
				break;
			}
		}
    }
    
	public void move() {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			if (b.isActive()) {
				b.shift(-5);
				if (b.isHit())
					b.deactive();
			}
		}
	}
	
}
