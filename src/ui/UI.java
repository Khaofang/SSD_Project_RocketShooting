package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import model.Bullet;
import model.Enemy;
import model.Game;
import model.Opponent;

public class UI extends JFrame implements KeyListener, Observer {

	private static ImageIcon II_BULLET_ENEMY = new ImageIcon("src/res/enemy_bullet.png");
	private static ImageIcon II_BULLET_ROCKET = new ImageIcon("src/res/rocket_bullet.png");
	private static ImageIcon II_ENEMY = new ImageIcon("src/res/enemy.png");
	private static ImageIcon II_ROCKET = new ImageIcon("src/res/rocket.png");
	private static ImageIcon II_OBSTACLE = new ImageIcon("src/res/obstacle.png");
	private static int UI_WIDTH = 840;
	private static int UI_HEIGHT = 480;

	private JPanel panel;

	private List<JLabel> lblEnemyBullet;
	private List<JLabel> lblOpponent;
	private List<JLabel> lblRocketBullet;
	private JLabel lblPressToStart;
	private JLabel lblRocket;
	private JLabel lblScore;
	private JLabel lblTime;

	// TODO: Component

	private Game game;

	public UI(Game game) {
		this.game = game;
		panel = (JPanel) getContentPane();
		panel.setPreferredSize(new Dimension(UI_WIDTH, UI_HEIGHT));
		panel.setBackground(new Color(186, 205, 168));
		panel.setLayout(null);
		initComponent();
		this.addKeyListener(this);

		setTitle("Rocket Shooting v0.35");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
	}

	public void run() {
		setVisible(true);
	}

	public void initComponent() {
		lblEnemyBullet = new ArrayList<JLabel>();
		for (int i = 0; i < 120; i++) {
			lblEnemyBullet.add(new JLabel(II_BULLET_ENEMY));
			panel.add(lblEnemyBullet.get(i));
			lblEnemyBullet.get(i).setVisible(false);
		}

		lblOpponent = new ArrayList<JLabel>();
		for (int i = 0; i < 3; i++) {
			lblOpponent.add(new JLabel(II_ENEMY));
			panel.add(lblOpponent.get(i));
			lblOpponent.get(i).setVisible(false);
		}
		lblOpponent.add(new JLabel(II_OBSTACLE));
		panel.add(lblOpponent.get(3));
		lblOpponent.get(3).setVisible(false);
		for (int i = 4; i < 7; i++) {
			lblOpponent.add(new JLabel(II_ENEMY));
			panel.add(lblOpponent.get(i));
			lblOpponent.get(i).setVisible(false);
		}
		lblOpponent.add(new JLabel(II_OBSTACLE));
		panel.add(lblOpponent.get(7));
		lblOpponent.get(7).setVisible(false);

		lblRocketBullet = new ArrayList<JLabel>();
		for (int i = 0; i < 20; i++) {
			lblRocketBullet.add(new JLabel(II_BULLET_ROCKET));
			panel.add(lblRocketBullet.get(i));
			lblRocketBullet.get(i).setVisible(false);
		}

		lblPressToStart = new JLabel(" < SPACE TO START > ");
		lblPressToStart.setFont(new Font(lblPressToStart.getFont().toString(), Font.BOLD, 32));
		panel.add(lblPressToStart);
		lblPressToStart.setBounds(450, 240, lblPressToStart.getPreferredSize().width,
				lblPressToStart.getPreferredSize().height);

		lblRocket = new JLabel(II_ROCKET);
		panel.add(lblRocket);
		lblRocket.setBounds(16, 240, 64, 64);
		game.getRocket().setY(240);

		lblScore = new JLabel(String.format("%05d", game.getScore()));
		lblScore.setFont(new Font(lblPressToStart.getFont().toString(), Font.BOLD, 32));
		panel.add(lblScore);
		lblScore.setBounds(720, 0, lblScore.getPreferredSize().width, lblScore.getPreferredSize().height);

		lblTime = new JLabel("Time: 00.00");
		lblTime.setFont(new Font(lblPressToStart.getFont().toString(), Font.BOLD, 32));
		panel.add(lblTime);
		lblTime.setBounds(0, 0, lblTime.getPreferredSize().width, lblTime.getPreferredSize().height);
	}

	@Override
	public void update(Observable o, Object arg) {
		long currTime = game.getTime();
		int currSec = ((int) (currTime / 1000)) % 60;
		int currMin = ((int) (currTime / 60000));
		lblTime.setText(String.format("Time: %02d:%02d", currMin, currSec));
		lblTime.setBounds(0, 0, lblTime.getPreferredSize().width, lblTime.getPreferredSize().height);

		lblScore.setText(String.format("%05d", game.getScore()));
		lblScore.setBounds(720, 0, lblScore.getPreferredSize().width, lblScore.getPreferredSize().height);

		for (int i = 0; i < 20; i++) {
			Bullet b = game.getRocket().getBulletPool().getBullets().get(i);
			JLabel lbl = lblRocketBullet.get(i);
			if (b.isActive()) {
				lbl.setVisible(true);
				int x = b.getX();
				int y = b.getY();
				lbl.setBounds(x, y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
			} else {
				lbl.setVisible(false);
			}
		}

		for (int i = 0; i < 8; i++) {
			Opponent op = game.getOp().getOpponents().get(i);
			JLabel lbl = lblOpponent.get(i);
			if (op.isActive() && !op.isHided()) {
				lbl.setVisible(true);
				int x = op.getX();
				int y = op.getY();
				lbl.setBounds(x, y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
			} else {
				lbl.setVisible(false);
			}
		}

		for (int i = 0; i < 8; i++) {
			Opponent op = game.getOp().getOpponents().get(i);
			if (!op.isObstacle()) {
				Enemy e = (Enemy) op;
				int ebpi = e.getBulletPoolIndex();
				for (int j = 0; j < 20; j++) {
					JLabel lbl = lblEnemyBullet.get(j + (20 * ebpi));
					Bullet b = e.getBulletPool().getBullets().get(j);
					if (b.isActive()) {
						lbl.setVisible(true);
						int x = b.getX();
						int y = b.getY();
						lbl.setBounds(x, y, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
					} else {
						lbl.setVisible(false);
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			lblPressToStart.setVisible(false);

		if (game.isPlaying()) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				game.moveRocketUp(64, 64);
				int y = game.getRocket().getY();
				lblRocket.setBounds(16, y, 64, 64);
			} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.moveRocketDown(64, 416);
				int y = game.getRocket().getY();
				lblRocket.setBounds(16, y, 64, 64);
			}
		} else {
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
				game.startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		Game g = new Game();
		UI ui = new UI(g);
		g.addObserver(ui);
		ui.run();
	}

}