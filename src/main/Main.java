package main;

import model.Game;
import model.GameReplay;
import ui.UIMain;
import ui.UIReplay;

public class Main {

	public static void main(String[] args) {
		Game g = new Game();
		GameReplay gr = g.getReplay();
		UIMain uiMain = new UIMain(g);
		g.addObserver(uiMain);
		uiMain.run();
	}
	
}
