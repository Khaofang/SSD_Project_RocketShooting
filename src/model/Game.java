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

	// TODO: implement other attributes later

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

	public void hitOpponent() {
		List<Bullet> bullets = rocket.getBulletPool().getBullets();
		List<Opponent> opponents = op.getOpponents();

		for (Opponent o : opponents) {
			if (o.isActive() && !o.isHided()) {
				for (Bullet b : bullets) {
					if (o.getX() - b.getX() < 32 && b.getY() - o.getY() == 28) {
						System.out.println("HIT!");
						o.hide();
						b.deactive();
						//score += 10;
					}
				}
			}
		}
	}

	public boolean isPlaying() {
		return playing;
	}

	public boolean isRocketHitOpponent() {
		for (Opponent o : op.getOpponents()) {
			if (o.isActive() && !o.isHided() && Math.abs(rocket.getX() - o.getX()) < 64 && rocket.getY() == o.getY())
				return true;
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
					if (i % 30 == 3)
						op.launch();
					time += 40;

					if (isRocketHitOpponent()) {
						end();
						JOptionPane.showMessageDialog(null, "Game Over!");
						break;
					}

					hitOpponent();

					// TODO: other tasks later

					i++;
					setChanged();
					notifyObservers();

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