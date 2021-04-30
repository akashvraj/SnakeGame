package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple {
	public int appleX;	//Position of Apple
	public int appleY; //Position of Apple
	public Color color;
	int appleEaten;
	
	public void dwawApple(Graphics g) {
		//Draw Apple
		g.setColor(color);
		g.fillOval(appleX,appleY,GamePanel.UNIT_SIZE,GamePanel.UNIT_SIZE);
	}
	
	public void newApple() {
		Random random = new Random();
		appleX = random.nextInt((int)(GamePanel.SCREEN_WIDTH/GamePanel.UNIT_SIZE))*GamePanel.UNIT_SIZE;
		appleY = random.nextInt((int)(GamePanel.SCREEN_HEIGHT/GamePanel.UNIT_SIZE))*GamePanel.UNIT_SIZE;
		System.out.println("Apple: " +appleX + "   " + appleY);
	}
}

/*Akash Verma*/