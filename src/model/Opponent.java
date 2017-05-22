package model;

public interface Opponent {

	public void deactive();
	public boolean inMap();
	public void interrupt();
	public boolean isActive();
	public void setStart();
	public void shift();
	
}