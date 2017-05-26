package model;

public class Enemy extends Opponent {

	private EnemyBulletPool bulletpool;
	private int bulletpoolIndex;

	public Enemy(int i) {
		super();
		bulletpool = EnemyBulletPool.getInstance()[i];
		bulletpoolIndex = i;
	}

	public EnemyBulletPool getBulletPool() {
		return bulletpool;
	}

	public int getBulletPoolIndex() {
		return bulletpoolIndex;
	}

	@Override
	public void hide() {
		hided = true;
		x = 1000;
		y = 1000;
	}

	@Override
	public void interrupt() {
		bulletpool.launch(x, y);
	}

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public void move() {
		x -= 5;
	}

}
