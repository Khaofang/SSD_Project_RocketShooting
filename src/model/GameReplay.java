package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.Game.GameData;

public class GameReplay extends Observable {
	
	private ReplayData replay;
	private boolean finishReplay;
	
	public GameReplay() {
		finishReplay = true;
		replay = new ReplayData();
	}
	
	public void save(GameData gd) {
		replay.save(gd);
	}
	
	public void replay() {
		finishReplay = false;
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (replay.remain()) {
					GameData gd = replay.load();
					
					System.out.println("UPDATE!");
					System.out.println("Current rocket pos: " + gd.getRocketPos()[0] + ", " + gd.getRocketPos()[1]);
					setChanged();
					notifyObservers(gd);
					try {
						Thread.sleep(40);
					} catch (Exception e) {
					}
				}
				finishReplay = true;
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
		
		private boolean remain() {
			return !datas.isEmpty();
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
