package main;

import model.Game;
import model.GameReplay;
import ui.UIMain;
import ui.UIReplay;

public class Main {

	public static void main(String[] args) {
		Game g = new Game();
		GameReplay gr = g.getReplay();
		UIReplay uiReplay = new UIReplay(gr);
		UIMain uiMain = new UIMain(g);
		g.addObserver(uiMain);
		uiMain.run();
	}
	
}
