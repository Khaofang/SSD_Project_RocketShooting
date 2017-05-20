package model;

import java.util.List;
import java.util.Observable;

public class Game extends Observable {

	private Rocket rocket;
	private List<Opponent> opponents;
	
	private boolean playing;
	// TODO: implement other attributes later
	
	public Game() {
		rocket = Rocket.getInstance();
	}
	
	public Rocket getRocket() {
		return rocket;
	}
	
	public boolean isPlaying() {
		return playing;
	}
	
	public void startGame() {
		playing = true;
	}
	
	public class GameData {
		
		private int[][] rocketBulletPos;
		private int rocketY;
		
	}
	
	private class ReplayData {
		
	}
	
	public class UpdateData {
		
	}
	
}
