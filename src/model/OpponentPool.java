package model;

import java.util.ArrayList;
import java.util.List;

public class OpponentPool {

	List<Opponent> opponents;
	
	public OpponentPool() {
		opponents = new ArrayList<Opponent>();
		opponents.add(new Obstacle());
		for (int i = 0; i < 3; i++)
			opponents.add(new Enemy());
		opponents.add(new Obstacle());
		for (int i = 0; i < 3; i++)
			opponents.add(new Enemy());
	}
	
	public void launch() {
		boolean addMore = false;
		for (int i = 0; i < opponents.size(); i++) {
			Opponent o = opponents.get(i);
			if (o.isActive()) {
				o.shift();
				if (!o.inMap())
					o.deactive();
			} else if (!addMore) {
				o.setStart();
			}
		}
	}
	
}
