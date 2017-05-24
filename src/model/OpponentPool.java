package model;

import java.util.ArrayList;
import java.util.List;

public class OpponentPool {
	
	private static OpponentPool instance = null;

	private List<Opponent> opponents;
	
	private OpponentPool() {
		opponents = new ArrayList<Opponent>();
		for (int i = 0; i < 3; i++)
			opponents.add(new Enemy());
		opponents.add(new Obstacle());
		for (int i = 0; i < 3; i++)
			opponents.add(new Enemy());
		opponents.add(new Obstacle());
	}
	
	public static OpponentPool getInstance() {
		if (instance == null)
			instance = new OpponentPool();
		return instance;
	}
	
	public void move() {
		for (int i = 0; i < opponents.size(); i++) {
			Opponent op = opponents.get(i);
			if (op.isActive()) {
				op.move();
				if (!op.inMap())
					op.deactive();
			}
		}
	}
	
	public void launch() {
		boolean addMore = false;
		for (int i = 0; i < opponents.size(); i++) {
			Opponent op = opponents.get(i);
			if (!op.isActive() && !addMore) {
				op.active();
				addMore = true;
			}
		}
	}
	
	public List<Opponent> getOpponents() {
		return opponents;
	}
	
}
