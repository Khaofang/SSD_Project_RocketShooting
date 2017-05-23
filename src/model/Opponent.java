package model;

public interface Opponent {

	public int getX();
	public int getY();
	public void active();
	public void deactive();
	public boolean inMap();
	public void interrupt();
	public boolean isActive();
	public void move();
	
}