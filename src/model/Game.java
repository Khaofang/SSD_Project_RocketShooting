package model;

import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Game extends Observable {

	private OpponentPool op;
	private Rocket rocket;
	private boolean playing;
	private int score;
	private long time;

	public Game() {
		rocket = Rocket.getInstance();
		op = OpponentPool.getInstance();
	}

	public OpponentPool getOp() {
		return op;
	}

	public Rocket getRocket() {
		return rocket;
	}

	public int getScore() {
		return score;
	}

	public long getTime() {
		return time;
	}

	public void end() {
		playing = false;
	}

	public boolean isPlaying() {
		return playing;
	}

	public boolean isRocketHitOpponent() {
		for (Opponent o : op.getOpponents()) {
			if (o.isActive() && !o.isHided() && Math.abs(o.getX() - rocket.getX()) < 60 && rocket.getY() == o.getY())
				return true;
		}

		return false;
	}
	
	public boolean isRocketHitEnemyBullet() {
		
		/*
		 *	Done Checking if the Rocket is got hit by an enemy bullet.
		 *
		 *  #BUG#
		 *  If they are too many enemy visible and you got hit by the last enemy
		 * 	they are slightly chances of that the rocket will survive from getting shot.
		 * 
		 */
		
		EnemyBulletPool[] ebp = EnemyBulletPool.getInstance();
		int rX = rocket.getX();
		int rY = rocket.getY();
		for (int i = 0; i < ebp.length; i++) {

			for(Bullet enemyBullet : ebp[i].getBullets()){
				if( Math.abs( enemyBullet.getX() - rX ) < 60 && Math.abs( enemyBullet.getY() - rY ) < 30 ){
					return true;
				}
				
			}
		
		}
		
		return false;
	}

	public boolean moveRocketDown(int rocketSize, int upperBound) {
		if (rocket.getY() + rocketSize >= upperBound)
			return false;

		rocket.move(rocketSize);
		return true;
	}

	public boolean moveRocketUp(int rocketSize, int lowerBound) {
		if (rocket.getY() <= lowerBound)
			return false;

		rocket.move((-1) * rocketSize);
		return true;
	}
	
	public void rocketBulletHitOpponent() {
		List<Bullet> bullets = rocket.getBulletPool().getBullets();
		List<Opponent> opponents = op.getOpponents();

		for (Opponent o : opponents) {
			if (o.isActive() && !o.isHided()) {
				for (Bullet b : bullets) {
					if (b.isActive() && o.getX() - b.getX() < 32 && b.getY() - o.getY() == 28) {
						o.hide();
						b.deactive();
						if (!o.isObstacle())
							score += 10;
					}
				}
			}
		}
	}

	public void startGame() {
		playing = true;
		Thread thread = new Thread() {
			@Override
			public void run() {
				long i = 0;
				time = 0;
				while (isPlaying()) {
					rocket.getBulletPool().move();
					if (i % 10 == 3)
						rocket.shoot();
					op.move();
					if (i % 35 == 3)
						op.launch();
					if (i % 20 == 3)
						op.allShoot();
					op.moveBullet();
					rocketBulletHitOpponent();
					time += 40;

					// TODO: other tasks later

					i++;
					setChanged();
					notifyObservers();

					if (isRocketHitOpponent()) {
						end();
						JOptionPane.showMessageDialog(null, "Game Over!");
						break;
					}
					
					if (isRocketHitEnemyBullet()) {
						end();
						JOptionPane.showMessageDialog(null, "Game Over!");
						break;
					}

					try {
						Thread.sleep(40);
					} catch (Exception e) {
					}
				}
			}
		};
		thread.start();
	}

	public class GameData {

		private int[][] rocketBulletPos;
		private int rocketY;
		private long currTime;

	}

	private class ReplayData {

		List<GameData> datas;

	}

}