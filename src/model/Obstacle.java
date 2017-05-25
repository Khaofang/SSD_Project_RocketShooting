package model;

public class Obstacle extends Opponent {
	
	public Obstacle() {
		super();
	}
	
	@Override
	public void hide() {
	}
	
	@Override
	public void interrupt() {
	}
	
	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public void move() {
		x -= 5;
	}

}
