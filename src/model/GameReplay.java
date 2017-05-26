package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.Game.GameData;

public class GameReplay extends Observable {

	private ReplayData replay;
	private boolean finishReplay;
	private int iData = 0;
	private Thread thread;

	public GameReplay() {
		finishReplay = true;
		replay = new ReplayData();

	}

	public boolean getFinishReplay() {
		return finishReplay;
	}

	public void save(GameData gd) {
		replay.save(gd);
	}

	public void replay() {
		iData = 0;
		thread = new Thread() {
			@Override
			public void run() {
				while (replay.remain()) {
					GameData gd = replay.load();
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
		finishReplay = false;
		thread.start();
	}

	public void clear() {
		finishReplay = true;
		thread = null;
		replay.clear();
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
			GameData gd = datas.get(iData);
			iData++;
			return gd;
		}

		private boolean remain() {
			return iData < datas.size() - 1;
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
