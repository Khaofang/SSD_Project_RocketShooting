package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import model.GameReplay.ReplayData;

public class Game extends Observable {

	private OpponentPool op;
	private GameReplay replay;
	private Rocket rocket;
	private boolean over;
	private boolean playing;
	private int highScore;
	private int score;
	private long time;

	public Game() {
		op = OpponentPool.getInstance();
		replay = new GameReplay();
		rocket = Rocket.getInstance();
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
	
	public GameReplay getReplay() {
		return replay;
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
		
		GameData gd = new GameData(this);
		replay.save(gd);
		
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
	
	public GameData load() {
		return null;
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
		score = 0;
		time = 0;
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
	
	public void saveData() {
		GameData gd = new GameData(this);
		replay.save(gd);
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


					i++;
					setChanged();
					notifyObservers();

					saveData();
					
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

		private boolean gameOver;
		private int[][] enemyBulletPos;
		private int[][] enemyPos;
		private int[][] obstaclePos;
		private int[][] rocketBulletPos;
		private int[] rocketPos;
		private long currTime;
		
		public GameData(Game g) {
			gameOver = g.isOver();
			enemyBulletPos = new int[120][2];
			enemyPos = new int[6][2];
			obstaclePos = new int[2][2];
			rocketBulletPos = new int[20][2];
			rocketPos = new int[2];	
			currTime = g.getTime();
			rocketPos[0] = 16;
			rocketPos[1] = g.getRocket().getY();
			
			RocketBulletPool rbp = g.getRocket().getBulletPool();
			OpponentPool op = g.getOp();
			EnemyBulletPool[] ebps = EnemyBulletPool.getInstance();
			
			for (int i = 0; i < 20; i++) {
				Bullet b = rbp.getBullets().get(i);
				rocketBulletPos[i][0] = b.getX();
				rocketBulletPos[i][1] = b.getY();
			}
			
			int ei = 0, oi = 0;
			for (int i = 0; i < 8; i++) {
				Opponent o = op.getOpponents().get(i);
				if (o.isObstacle()) {
					obstaclePos[oi][0] = o.getX();
					obstaclePos[oi][1] = o.getY();
					oi++;
				} else {
					enemyPos[oi][0] = o.getX();
					enemyPos[oi][1] = o.getY();
					ei++;
				}
				
			}
			
			int bi = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 20; j++) {
					Bullet b = ebps[i].getBullets().get(j);
					enemyBulletPos[bi][0] = b.getX();
					enemyBulletPos[bi][1] = b.getY();
					bi++;
				}
			}
			
		}
		
		
		public long getCurrTime() {
			return currTime;
		}
		
		public int[][] getEnemyBulletPos() {
			return enemyBulletPos;
		}
		
		public int[][] getEnemyPos() {
			return enemyPos;
		}
		
		public int[][] getObstaclePos() {
			return obstaclePos;
		}
		
		public int[][] getRocketBulletPos() {
			return rocketBulletPos;
		}
		
		public int[] getRocketPos() {
			return rocketPos;
		}
		
		public boolean getGameOver() {
			return gameOver;
		}

	}

}