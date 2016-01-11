package lab10.snake;

import java.awt.Color;
import java.util.LinkedList;

import lab10.snake.Food;
import sedgewick.StdDraw;

public class ControlFromInterface {

	/**
	 * displays the player's score for the previous game and waits from
	 * a response from the player before resetting for a new game
	 */
	public static void gameOver(int score) {
		StdDraw.clear();
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(.5, .9, "Darn!");
		StdDraw.text(.5, .8, "Looks like it wasn't your lucky day, huh?");
		StdDraw.text(.5,  .75, "Wanna try again?");
		StdDraw.text(.5, .7, "Press press any key to continue or close out.");
		String scoreString = ("Your score was a whopping " + score + " points!!");
		StdDraw.text(.5, .6, scoreString);
		char keyPressed = ' ';
		while (keyPressed == ' ') {
			while (!StdDraw.hasNextKeyTyped())
				StdDraw.show(20);
			keyPressed = StdDraw.nextKeyTyped();
		}
		StdDraw.clear();
	}
	
	public static void main(String[] args) {
		LinkedList<Anim> scene1 = new LinkedList<Anim>();
		StartScreen ss = new StartScreen();
		scene1.add(ss);
		Snake s = new Snake();
		scene1.add(s);
		Food f = new Food();
		scene1.add(f);
		
		while (true) {
			StdDraw.clear();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.square(.5, .5, .5);
			
			for (Anim a : scene1) {
				if (s.isDone()) {
					gameOver(s.x.size());
					a.reset();
				}
				a.draw();
			}
			
			if (((double)s.x.getFirst() == (double)f.getX()) 
					&& ((double)s.y.getFirst() == (double)f.getY())) {
				s.addLast();
				f.setNew();
			}
			
			StdDraw.show(75);
		}
	}

}
