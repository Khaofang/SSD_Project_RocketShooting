package model;

import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

public class Game extends Observable {

	private OpponentPool op;
	private Rocket rocket;
	private boolean over;
	private boolean playing;
	private int highScore;
	private int score;
	private long time;

	public Game() {
		rocket = Rocket.getInstance();
		op = OpponentPool.getInstance();
		over = false;
		playing = false;
		highScore = 0;
	}

	public OpponentPool getOp() {
		return op;
	}

	public Rocket getRocket() {
		return rocket;
	}
	
	public int getHighScore() {
		return highScore;
	}

	public int getScore() {
		return score;
	}

	public long getTime() {
		return time;
	}

	public void end() {
		over = true;
		playing = false;
		
		if (score > highScore)
			highScore = score;
	}
	
	public boolean isOver() {
		return over;
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
		EnemyBulletPool[] ebp = EnemyBulletPool.getInstance();
		int rX = rocket.getX();
		int rY = rocket.getY();
		for (int i = 0; i < ebp.length; i++) {
			for (Bullet eb : ebp[i].getBullets()) {
				if (eb.isActive() && Math.abs(eb.getX() - rX) <= 60 && eb.getY() - rY == 16)
					return true;
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
	
	public void reset() {
		over = false;
		playing = true;
		
		op.reset();
		rocket.reset();
		EnemyBulletPool[] ebp = EnemyBulletPool.getInstance();
		for (int i = 0; i < ebp.length; i++)
			ebp[i].reset();
		rocket.getBulletPool().reset();
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
				while (!over) {
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
						setChanged();
						notifyObservers();
						break;
					} else if (isRocketHitEnemyBullet()) {
						end();
						setChanged();
						notifyObservers();
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