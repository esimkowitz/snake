package lab10.snake;

import java.awt.Color;

import sedgewick.StdDraw;

public class StartScreen implements Anim {
	
	protected boolean isDone;

	public StartScreen() {
		this.isDone = false;
	}

	@Override
	public void draw() {
		if (!this.isDone) {
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
			this.isDone = true;
		}
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
	
	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void reset() {
		this.isDone = false;
	}

}
