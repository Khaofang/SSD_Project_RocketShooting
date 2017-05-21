package model;

import java.util.List;
import java.util.Observable;

public class Game extends Observable {

	private Rocket rocket;
	private List<Opponent> opponents;
	
	private boolean playing;
	private int score;
	// TODO: implement other attributes later
	
	public Game() {
		rocket = Rocket.getInstance();
	}
	
	public Rocket getRocket() {
		return rocket;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	public boolean moveRocketUp(int rocketSize, int lowerBound) {
		if (rocket.getY() <= lowerBound)
			return false;
		
		rocket.move((-1) * rocketSize);
		return true;
	}
	
	public boolean moveRocketDown(int rocketSize, int upperBound) {
		if (rocket.getY() + rocketSize >= upperBound)
			return false;
		
		rocket.move(rocketSize);
		return true;
	}
	
	public void startGame() {
		playing = true;
	}
	
	public class GameData {
		
		private int[][] rocketBulletPos;
		private int rocketY;
		
	}
	
	private class ReplayData {
		
		List<GameData> datas;
		
	}
	
	public class UpdateData {
		
	}
	
}
