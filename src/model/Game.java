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

	public boolean isPlaying() {
		return playing;
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

	public boolean isRocketHitOpponent() {

		/*	Add getX for rocket to find x position of rocket in x line
		 *  and using x line to check if the rocket is hitting the opponent
		 *  as the x position of x is equal 0, maybe we can delete getX() from rocket method
		 *  and use opList.getX() <= 60, and getY() as this game is fix position of y on each position
		 *  just check if they are in the same y position and check x position instead.
		 *  
		 * 	#BUG# if the rocket suddenly move behind the opponent instantly there are a bit of a chance
		 * 			that the game will game over even they are not collide or hitting each other.
		 * 			the procedure of fixing bug will leave it to you guys, Chayanin or Nuttapong.
		 */
		
		for(Opponent opList: op.getOpponents()) {
			if( opList.getX() - rocket.getX() < 60 && rocket.getY() == opList.getY() ) {
				return true;
			}
		}

		return false;
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
					if (i % 10 == 2)
						rocket.shoot();
					op.move();
					if (i % 30 == 2)
						op.launch();
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