package model;

public class Enemy extends Opponent {

	private EnemyBulletPool bulletpool;
	private int bulletpoolIndex;
	
	public Enemy(int i) {
		super();
		bulletpool = EnemyBulletPool.getInstance()[i];
		bulletpoolIndex = i;
	}
	
	@Override
	public void hide() {
		hided = true;
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
