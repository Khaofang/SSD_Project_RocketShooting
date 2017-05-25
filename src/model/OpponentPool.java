package model;

import java.util.ArrayList;
import java.util.List;

public class OpponentPool {

	private static OpponentPool instance = null;

	private List<Opponent> opponents;

	private OpponentPool() {
		int ei = 0;
		opponents = new ArrayList<Opponent>();
		for (int i = 0; i < 3; i++) {
			opponents.add(new Enemy(ei));
			ei++;
		}
		opponents.add(new Obstacle());
		for (int i = 0; i < 3; i++) {
			opponents.add(new Enemy(ei));
			ei++;
		}
		opponents.add(new Obstacle());
	}

	public static OpponentPool getInstance() {
		if (instance == null)
			instance = new OpponentPool();
		return instance;
	}
	
	public List<Opponent> getOpponents() {
		return opponents;
	}

	public void allShoot() {
		for (int i = 0; i < opponents.size(); i++) {
			Opponent op = opponents.get(i);
			if (!op.isObstacle() && op.isActive() && !op.isHided()) {
				op.interrupt();
			}
			op.move();
		}
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
	
	public void moveBullet() {
		for (int i = 0; i < opponents.size(); i++) {
			Opponent op = opponents.get(i);
			if (!op.isObstacle() && op.isActive()) {
				((Enemy) op).getBulletPool().move();
			}
		}
	}

	public void launch() {
		for (int i = 0; i < opponents.size(); i++) {
			Opponent op = opponents.get(i);
			if (!op.isActive()) {
				op.active();
				break;
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < opponents.size(); i++) {
			Opponent op = opponents.get(i);
			op.deactive();
		}
	}

}
