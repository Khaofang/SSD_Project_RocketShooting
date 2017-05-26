package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game.GameData;
import model.GameReplay;

public class UIReplay extends JFrame implements KeyListener, Observer {

	private static ImageIcon II_BULLET_ENEMY = new ImageIcon("src/res/enemy_bullet.png");
	private static ImageIcon II_BULLET_ROCKET = new ImageIcon("src/res/rocket_bullet.png");
	private static ImageIcon II_ENEMY = new ImageIcon("src/res/enemy.png");
	private static ImageIcon II_ROCKET = new ImageIcon("src/res/rocket.png");
	private static ImageIcon II_OBSTACLE = new ImageIcon("src/res/obstacle.png");
	private static int UI_WIDTH = 840;
	private static int UI_HEIGHT = 480;

	private JPanel panel;

	private List<JLabel> lblEnemy;
	private List<JLabel> lblEnemyBullet;
	private List<JLabel> lblObstacle;
	private List<JLabel> lblRocketBullet;
	private JLabel lblReplay;
	private JLabel lblRocket;

	private GameReplay game;

	public UIReplay(GameReplay g) {
		game = g;
		panel = (JPanel) getContentPane();
		panel.setPreferredSize(new Dimension(UI_WIDTH, UI_HEIGHT));
		panel.setBackground(new Color(186, 205, 168));
		panel.setLayout(null);
		this.addKeyListener(this);
		initComponent();
		setResizable(false);
		setTitle("Replay");
		pack();
		setVisible(false);
	}

	public void initComponent() {
		lblEnemy = new ArrayList<JLabel>();
		for (int i = 0; i < 6; i++) {
			lblEnemy.add(new JLabel(II_ENEMY));
			panel.add(lblEnemy.get(i));
			lblEnemy.get(i).setVisible(true);
		}
		lblEnemyBullet = new ArrayList<JLabel>();
		for (int i = 0; i < 120; i++) {
			lblEnemyBullet.add(new JLabel(II_BULLET_ENEMY));
			panel.add(lblEnemyBullet.get(i));
			lblEnemyBullet.get(i).setVisible(true);
		}

		lblObstacle = new ArrayList<JLabel>();
		for (int i = 0; i < 2; i++) {
			lblObstacle.add(new JLabel(II_OBSTACLE));
			panel.add(lblObstacle.get(i));
			lblObstacle.get(i).setVisible(true);
		}

		lblRocketBullet = new ArrayList<JLabel>();
		for (int i = 0; i < 20; i++) {
			lblRocketBullet.add(new JLabel(II_BULLET_ROCKET));
			panel.add(lblRocketBullet.get(i));
			lblRocketBullet.get(i).setVisible(true);
		}

		lblRocket = new JLabel(II_ROCKET);
		panel.add(lblRocket);
		lblRocket.setVisible(true);

		lblReplay = new JLabel("SPACE WHEN FINISH PREVIOUS: REPLAY / R: CLOSE");
		panel.add(lblReplay);
		lblReplay.setVisible(true);
		lblReplay.setFont(new Font(lblReplay.getFont().toString(), Font.BOLD, 24));
		lblReplay.setBounds(16, 0, lblReplay.getPreferredSize().width,
				lblReplay.getPreferredSize().height);
	}

	public void run() {
		setVisible(true);
		game.replay();
	}

	@Override
	public void update(Observable o, Object arg) {
		GameData gd = (GameData) arg;

		boolean gameOver = gd.getGameOver();
		int[][] enemyBulletPos = gd.getEnemyBulletPos();
		int[][] enemyPos = gd.getEnemyPos();
		int[][] obstaclePos = gd.getObstaclePos();
		int[][] rocketBulletPos = gd.getRocketBulletPos();
		int[] rocketPos = gd.getRocketPos();
		long currTime = gd.getCurrTime();

		lblRocket.setBounds(rocketPos[0], rocketPos[1], lblRocket.getPreferredSize().width,
				lblRocket.getPreferredSize().height);
		lblRocket.setVisible(true);
		for (int i = 0; i < 20; i++) {
			lblRocketBullet.get(i).setBounds(rocketBulletPos[i][0], rocketBulletPos[i][1],
					lblRocketBullet.get(i).getPreferredSize().width, lblRocketBullet.get(i).getPreferredSize().height);
		}

		for (int i = 0; i < 6; i++) {
			lblEnemy.get(i).setBounds(enemyPos[i][0], enemyPos[i][1], lblEnemy.get(i).getPreferredSize().width,
					lblEnemy.get(i).getPreferredSize().height);
		}
		for (int i = 0; i < 120; i++) {
			lblEnemyBullet.get(i).setBounds(enemyBulletPos[i][0], enemyBulletPos[i][1],
					lblEnemyBullet.get(i).getPreferredSize().width, lblEnemyBullet.get(i).getPreferredSize().height);
		}

		for (int i = 0; i < 2; i++) {
			lblObstacle.get(i).setBounds(obstaclePos[i][0], obstaclePos[i][1],
					lblObstacle.get(i).getPreferredSize().width, lblObstacle.get(i).getPreferredSize().height);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (game.getFinishReplay())
				game.replay();
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			game.clear();
			setVisible(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
