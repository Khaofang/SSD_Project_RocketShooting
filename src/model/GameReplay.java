package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.Game.GameData;

public class GameReplay extends Observable {
	
	ReplayData replay;
	
	public GameReplay() {
		replay = new ReplayData();
	}
	
	public void save(GameData gd) {
		replay.save(gd);
	}
	
	public void replay() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					GameData gd = replay.load();
					setChanged();
					notifyObservers(gd);
					try {
						Thread.sleep(40);
					} catch (Exception e) {
					}
					
					if(gd.getGameOver())
						break;
				}
			}
		};
		thread.start();	
	}

	public class ReplayData {

		List<GameData> datas;
		
		private ReplayData() {
			datas = new ArrayList<GameData>();
		}
		
		private void clear() {
			datas.clear();
		}
		
		private GameData load() {
			GameData gd = datas.get(0);
			datas.remove(0);
			return gd;
		}
		
		private void save(GameData gd) {
			datas.add(gd);
			
			while (true) {
				GameData firstData = datas.get(0);
				if (gd.getCurrTime() - firstData.getCurrTime() > 5000)
					datas.remove(0);
				else
					break;
			}
		}
		
	}
	
}
