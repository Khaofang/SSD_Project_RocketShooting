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
	
	private static ImageIcon iiRocket = new ImageIcon("src/res/rocket.png");
	private static ImageIcon iiEnemy = new ImageIcon("src/res/enemy.png");
	private static ImageIcon iiObstacle;
	
	private JPanel panel;
	private JPanel panelRocket;
	
	private JLabel lblRocket;
	private JLabel lblPressToStart;
	
	// TODO: Component
	
	private Game game;

	public UI(Game game) {
		this.game = game;
		panel = (JPanel) getContentPane();
		panel.setPreferredSize(new Dimension(840, 480));
		panel.setBackground(new Color(186, 205, 168));
		panel.setLayout(null);
		initComponent();
		
		setTitle("Rocket Shooting");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
	}
	
	public void run() {
		setVisible(true);
	}
	
	public void initComponent() {
		lblRocket = new JLabel(iiRocket);
		panel.add(lblRocket);
		lblRocket.setBounds(16, 192, 64, 64);
		
		lblPressToStart = new JLabel("< SPACE TO START >");
		lblPressToStart.setFont(new Font(lblPressToStart.getFont().toString(), Font.BOLD, 32));
		panel.add(lblPressToStart);
		lblPressToStart.setBounds(480, 208, lblPressToStart.getPreferredSize().width, lblPressToStart.getPreferredSize().height);
		
		// TODO: implement more components
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (game.isPlaying()) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				// TODO: make rocket moves up
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				// TODO: make rocket moves down
			}
		} else {
			if (e.getKeyChar() == ' ') {
				// TODO: make label disappear and start game
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		Game g = new Game();
		UI ui = new UI(g);
		g.addObserver(ui);
		ui.run();
	}
	
}
