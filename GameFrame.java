package SnakeGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	public GameFrame() {
		this.setTitle("AV Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new GamePanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

/*Akash Verma*/