package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;

public class UI extends JFrame implements KeyListener, Observer {
	
	private JPanel panel;
	
	// TODO: Component
	
	// TODO: Attribute
	private Game game;

	public UI(Game game) {
		this.game = game;
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(840, 480));
		panel.setBackground(new Color(186, 205, 168));
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(840, 480));
		initComponent();
		add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
	}
	
	public void run() {
		setVisible(true);
	}
	
	public void initComponent() {
		// TODO: implement components
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Case Game is not started
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
		}
		
		// Case Game is playing
		
		
		// TODO Auto-generated method stub

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
		ui.run();
	}
	
}
