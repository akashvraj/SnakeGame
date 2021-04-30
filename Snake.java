package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	//Body of snake
	public final int x[];
	public final int y[];

	public int bodyParts = 6;

	public char direction;

	public Snake(int size) {
		x = new int[size];
		y = new int[size];
	}

	public void drawSnake(Graphics g)
	{
		//Draw Snake
		for(int i=0; i<bodyParts ; i++) {
			if(i==0){	//Head of snake
				g.setColor(Color.RED);
				g.fillRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
			} else {	//Rest Body of snake
				g.setColor(new Color(45,180,10));
				g.fill3DRect(x[i], y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE, true);
			}
		}
	}

	public void debugSnakeBody() {
		System.out.print("[");
		for(int i=0; i<=bodyParts; i++) {
			System.out.print(x[i]+","+y[i] + "   ");
		}
		System.out.println("]");
	}

	public void move(char direction) {

		//Move Snake-Body
		for(int i=bodyParts; i>0; i--){
			x[i] = x[i-1];
			y[i] = y[i-1];
		}

		//Move Head
		switch(direction){
		case 'U': y[0] = y[0] - GamePanel.UNIT_SIZE; break;
		case 'D': y[0] = y[0] + GamePanel.UNIT_SIZE; break;
		case 'R': x[0] = x[0] + GamePanel.UNIT_SIZE; break;
		case 'L': x[0] = x[0] - GamePanel.UNIT_SIZE; break;
		}

		//		debugSnakeBody();
	}
}

/*Akash Verma*/