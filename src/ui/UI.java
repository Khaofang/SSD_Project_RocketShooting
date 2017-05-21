package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class UI extends JFrame implements KeyListener, Observer {
	
	private static ImageIcon II_ROCKET = new ImageIcon("src/res/rocket.png");
	private static ImageIcon II_ENEMY = new ImageIcon("src/res/enemy.png");
	private static ImageIcon II_OBSTACLE; // Not implements resource yet
	private static int UI_WIDTH = 840;
	private static int UI_HEIGHT = 480;
	
	private JPanel panel;
	
	private JLabel lblRocket;
	private JLabel lblPressToStart;
	private JLabel lblScore;
	
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
		
		setTitle("Rocket Shooting");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
	}
	
	public void run() {
		setVisible(true);
	}
	
	public void initComponent() {
		lblRocket = new JLabel(II_ROCKET);
		panel.add(lblRocket);
		lblRocket.setBounds(16, 240, 64, 64);
		game.getRocket().setY(240);
		
		lblPressToStart = new JLabel("< SPACE TO START >");
		lblPressToStart.setFont(new Font(lblPressToStart.getFont().toString(), Font.BOLD, 32));
		panel.add(lblPressToStart);
		lblPressToStart.setBounds(480, 240, lblPressToStart.getPreferredSize().width, lblPressToStart.getPreferredSize().height);
		
		lblScore = new JLabel(String.format("%05d", game.getScore()));
		lblScore.setFont(new Font(lblPressToStart.getFont().toString(), Font.BOLD, 32));
		panel.add(lblScore);
		lblScore.setBounds(740, 0, lblScore.getPreferredSize().width, lblScore.getPreferredSize().height);
		
		// TODO: implement more components
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Game.UpdateData) {
			// TODO: update ui with update data from game
		}
		
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		System.out.println("Key is typing : " + e.getKeyCode());
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
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				game.startGame();
				lblPressToStart.setVisible(false);
				panel.remove(lblPressToStart);
			}
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
