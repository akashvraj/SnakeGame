package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class GamePanel extends JPanel implements ActionListener
{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 15;	//How big we want items in our game
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT) / (UNIT_SIZE*UNIT_SIZE);
	static final int INITIAL_DELAY = 100;
	
	int delay = INITIAL_DELAY;
	
	Snake snake;
	Apple apple;
	
	boolean running = false;

	Timer timer;

	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_WIDTH));
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		apple = new Apple();
		apple.color = Color.RED;

		startGame();
	}

	private void startGame() {
		running = true;
		snake = new Snake(GAME_UNITS);
		snake.direction = 'R';
		apple.newApple();
		timer = new Timer(INITIAL_DELAY, this);
		timer.start();

	}

	private void checkApple() {
		if(snake.x[0]==apple.appleX && snake.y[0]==apple.appleY) {
			snake.bodyParts++;
			apple.appleEaten++;
			apple.newApple();

			//Increase speed of game
			delay -= ((apple.appleEaten/5)*3);
			timer.setDelay(delay);
		}
	}

	private void checkCollosions() {

		//Check if head touches right border
		if(snake.x[0]>SCREEN_WIDTH) { running = false; }
		//Check if head touches left border
		else if(snake.x[0]<0) { running = false; }
		//Check if head touches top border
		else if(snake.y[0]<0) { running = false; }
		//Check if head touches bottom border
		else if(snake.y[0]>SCREEN_HEIGHT) { running = false; }

		//Check if head collide with its body
		for(int i=snake.bodyParts; i>=4; i--) {
			if(snake.x[0]==snake.x[i] && snake.y[0]==snake.y[i]){
				running = false;
			}
		}

		if(!running) { timer.stop(); }
	}

	private void gameOver(Graphics g) {
		drawGameOverText(g);
		drawScore(g);
	}

	private void draw(Graphics g) {
		if(running) {
			//drawGrid(g);
			snake.drawSnake(g);
			apple.dwawApple(g);
			drawScore(g);
		} else
			gameOver(g);
	}


	/* Helper methods */
	private void drawScore(Graphics g) {
		//Draw Score
		g.setColor(Color.CYAN);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics fontMetrics = getFontMetrics(g.getFont());
		g.drawString("Score: "+apple.appleEaten, 
				(SCREEN_WIDTH-fontMetrics.stringWidth("Score: "+apple.appleEaten))/2, 
				g.getFont().getSize());
	}

	private void drawGrid(Graphics g) {
		//Draw Horizontal/Vertical lines
		for(int i=0; i<SCREEN_WIDTH/UNIT_SIZE; i++) {
			g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
		}
	}

	private void drawGameOverText(Graphics g) {
		//Game Over Text
		g.setColor(Color.GREEN);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics fontMetrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH-fontMetrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}



	//implements ActionListener
	public void actionPerformed(ActionEvent e) {
		if(running) {
			snake.move(snake.direction);
			checkCollosions();
			checkApple();
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) 
			{
			case KeyEvent.VK_LEFT: 
				if(snake.direction != 'R') snake.direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				if(snake.direction != 'L') snake.direction = 'R';
				break;
			case KeyEvent.VK_UP:
				if(snake.direction != 'D') snake.direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				if(snake.direction != 'U') snake.direction = 'D';
				break;
			case KeyEvent.VK_S:
				if(!running) {
					startGame();
				}
				break;
			}
		} // end keyPressed
	} //end MyKeyAdapter
}

/*Akash Verma*/