package lab10.snake;

import java.awt.Color;
import java.util.ListIterator;

import sedgewick.StdDraw;

public class Control {

	private Food f;
	private Snake s;

	public Control() {
		this.f = new Food();
		this.s = new Snake();
	}
	
	/**
	 * resets the snake, food, and score keeper
	 */
	public void reset() {
		this.s.reset();
		this.f.reset();
	}

	/**
	 * used to check if the snake has eaten its food, has run into a wall,
	 * or has hit itself
	 * @return true if the orientation value has been changed
	 */
	public boolean equalsCheck() {
		boolean newOrientation = false;
		if (((double)this.s.x.getFirst() == (double)this.f.getX()) 
				&& ((double)this.s.y.getFirst() == (double)this.f.getY())) {
			this.s.addLast();
			this.f.reset();
		}
		if (this.s.watchOut()) {
			this.gameOver();
			newOrientation = true;
		}
		if (this.s.atWall()) {
			this.gameOver();
			newOrientation = true;
		}
		return newOrientation;
	}
	
	/**
	 * builds each consecutive frame of the game
	 */
	public void build() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.square(.5, .5, .5);
		double size = 0.025/2.0;
		ListIterator<Double> liX = s.x.listIterator();
		ListIterator<Double> liY = s.y.listIterator();
		StdDraw.setPenColor(StdDraw.RED);
		double fx = f.getX() + size;
		double fy = f.getY() + size;
		StdDraw.filledSquare(fx, fy, size);
		StdDraw.text(0.075, 0.015, "Score: " + (this.s.x.size()-1));
		while (liX.hasNext()) {
			double sX = liX.next() + size;
			double sY = liY.next() + size;
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(sX, sY, size);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.line(sX+size, sY-size, sX+size, sY-size);
		}
		StdDraw.show(75);
	}
	
	/**
	 * provides the player with instructions on how to play the game
	 */
	public static void startScreen() {
		StdDraw.clear();
		StdDraw.setPenColor(Color.black);
		StdDraw.text(.5, .9, "Hello, welcome to Snake!!");
		StdDraw.text(.5, .85, "In this game, you are a snake.");
		StdDraw.text(.5, .8, "You are represented by a black line moving about the screen.");
		StdDraw.text(.5,  .75, "Your goal is to eat the red blocks. To do this, you can use");
		StdDraw.text(.5, .7, "either the WASD or arrow keys to change the");
		StdDraw.text(.5, .65, "direction your snake travels. Be careful though!");
		StdDraw.text(.5, .6, "If you try to go backwards, run into yourself, or run");
		StdDraw.text(.5, .55, "into a wall, it's game over!!");
		StdDraw.text(.5, .45, "If you think you've got what it takes, press any key to begin!");
		while (!StdDraw.hasNextKeyTyped()) {
			StdDraw.pause(10);
		}
		countdown();
	}
	
	/**
	 * displays the player's score for the previous game and waits from
	 * a response from the player before resetting for a new game
	 */
	public void gameOver() {
		StdDraw.clear();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(.5, .9, "Darn!");
		StdDraw.text(.5, .8, "Looks like it wasn't your lucky day, huh?");
		StdDraw.text(.5,  .75, "Wanna try again?");
		StdDraw.text(.5, .7, "Press (Y) to continue or close out.");
		String score = ("Your score was a whopping " + (this.s.x.size()-1) + " points!!");
		StdDraw.text(.5, .6, score);
		char keyPressed = ' ';
		while (keyPressed != 'y') {
			while (!StdDraw.hasNextKeyTyped())
				StdDraw.show(20);
			keyPressed = StdDraw.nextKeyTyped();
		}
		StdDraw.clear();
		countdown();
		this.reset();
	}
	
	/**
	 * a simple countdown from 3 to 0
	 */
	public static void countdown() {
		for (int i = 3; i >= 0; i--) {
			StdDraw.clear();
			StdDraw.setPenColor(Color.red);
			StdDraw.text(.5, .5, ("" + i));
			StdDraw.show(1000);
		}
		StdDraw.clear();
	}

	public static void main(String[] args) {
		startScreen();
		Control c = new Control();
		int orientation = c.s.orientation.getFirst();
		while (true) {
			orientation = c.s.orientation.getFirst();
			if (StdDraw.isKeyPressed(37)||StdDraw.isKeyPressed(65)) {
				if ((orientation != 3) || (c.s.x.size() == 1)) {
					orientation = 9;
				}
			}
			if (StdDraw.isKeyPressed(38)||StdDraw.isKeyPressed(87)) {
				if ((orientation != 6) || (c.s.x.size() == 1)) {
					orientation = 12;
				}			
			}
			if (StdDraw.isKeyPressed(39)||StdDraw.isKeyPressed(68)) {
				if ((orientation != 9) || (c.s.x.size() == 1))
					orientation = 3;
			}
			if (StdDraw.isKeyPressed(40)||StdDraw.isKeyPressed(83)) {
				if ((orientation != 12) || (c.s.x.size() == 1))
					orientation = 6;
			}
			c.s.blockIterator(orientation);
			boolean newOrientation = c.equalsCheck();
			if (newOrientation) {
				orientation = c.s.orientation.getFirst();
			}
			//System.out.println(c);
			c.build();
		}
	}

	@Override
	public String toString() {
		return "Control [f=" + f + ", s(first)=[" + s.x.getFirst() + "," + s.y.getFirst() + "], s=" + s + "]";
	}
	

}
