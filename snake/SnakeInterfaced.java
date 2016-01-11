package lab10.snake;

import java.util.LinkedList;
import java.util.ListIterator;

import sedgewick.StdDraw;

public class SnakeInterfaced implements Anim{
	
	// This was a failed attempt to make a separate Snake object for use with an interface.
	// I gave up on it for the most part and just use the Snake object for both controls.

	protected Point p;
	protected Integer orientation;	
	// 		12 = up
	// 		3 = right
	// 		6 = down
	// 		9 = left
	// 		think of a clock-face!!

	/**
	 * Constructor -- creates empty lists
	 */
	public SnakeInterfaced() {
		this.x = new LinkedList<Double>();
		this.y = new LinkedList<Double>();
		this.orientation = new LinkedList<Integer>();
	}

	/**
	 * Setter
	 * @param x = block's x coordinate (corresponds to lower left-hand corner)
	 * @param y = '' but for y instead of x
	 * @param orientation of block
	 * @return
	 */
	public SnakeInterfaced addTerm(double x, double y, int orientation) {
		this.x.addFirst(x);
		this.y.addFirst(y);
		this.orientation.addFirst(orientation);
		return this;
	}

	/**
	 * setter
	 * adds a different item to the end of each list 
	 * depending on the last term's orientation
	 * @return
	 */
	public SnakeInterfaced addLast() {
		int orientation = this.orientation.getLast();
		double x = this.x.getLast();
		double y = this.y.getLast();
		double size = .025;
		if (orientation==12)
			y = y - size;
		else if (orientation==3)
			x = x - size;
		else if (orientation==6)
			y = y + size;
		else
			x = x + size;
		this.x.addLast(x);
		this.y.addLast(y);
		this.orientation.addLast(orientation);
		return this;
	}

	/**
	 * setter
	 * @param x = same as in addTerm
	 * @param y = ''
	 * @param orientation = ''
	 * @param index = position in list
	 * @return replaces a term in the list with the specified parameters
	 */
	public SnakeInterfaced replaceTerm(double x, double y, int orientation, int index) {
		this.x.add(index, x);
		this.x.remove(index+1);
		this.y.add(index, y);
		this.y.remove(index+1);
		this.orientation.add(index, orientation);
		this.orientation.remove(index+1);
		return this;
	}

	/**
	 * Setter
	 * @return removes the final term from the list
	 */
	public SnakeInterfaced removeLast() {
		this.x.removeLast();
		this.y.removeLast();
		this.orientation.removeLast();
		return this;
	}

	/**
	 * setter
	 * @param index = some point in list
	 * @return removes term at specified point
	 */
	public SnakeInterfaced removeTerm(int index) {
		this.x.remove(index);
		this.y.remove(index);
		this.orientation.remove(index);
		return this;
	}

	/** 
	 * checks to see if the snake has run into itself
	 * @return
	 */
	public boolean watchOut() {
		ListIterator<Double> liX = this.x.listIterator();
		ListIterator<Double> liY = this.y.listIterator();
		double x = this.x.getFirst();
		double y = this.y.getFirst();
		liX.next();
		liY.next();
		while (liX.hasNext()) {
			if (liX.next() == x && liY.next() == y) {
				return true;
			}
		}
		return false;
	}

	/**
	 * detects if the snake has hit the wall, returns true if it has,
	 * false if it hasn't
	 * @return
	 */
	public boolean atWall() {
		if (((double)this.x.getFirst() == 1.0) || 
				((double)this.y.getFirst() == 1.0))
			return true;
		else if(((double)this.x.getFirst() == -0.025) || 
				((double)this.y.getFirst() == -0.025))
			return true;
		else
			return false;
	}

	/**
	 * initializes the game with (numBlocks) number of blocks in the orientation specified
	 * @param orientation
	 */
	public void initializer(int orientation) {
		int numBlocks = 1;
		double size = .025;
		double randomX = Math.random();
		double randomY = Math.random();
		randomX = (double)Math.round(randomX/size);
		randomY = (double)Math.round(randomY/size);
		randomX = (double)Math.round((randomX*size)*1000)/1000;
		randomY = (double)Math.round((randomY*size)*1000)/1000;
		for (int i=0; i<numBlocks; i++) {
			size = (double)(size*i);
			if (orientation==12)
				this.addTerm(randomX, randomY+size, orientation);
			else if (orientation==3)
				this.addTerm(randomX+size, randomY, orientation);
			else if (orientation==6)
				this.addTerm(randomX, randomY-size, orientation);
			else
				this.addTerm(randomX-size, randomY, orientation);
		}
	}

	/**
	 * returns a randomly chosen orientation for a block
	 * @return orientation
	 */
	public int orientationScrambler() {
		double random = Math.random();
		if (random < .25)
			return 12;
		else if (random < .5)
			return 3;
		else if (random < .75)
			return 6;
		else 
			return 9;
	}

	/**
	 * iterates the list to setup next frame of the game
	 */
	public void blockIterator(int orientation) {
		double size = .025;
		if (orientation==12) {
			double y1 = (double)Math.round((y.getFirst()+size)*1000)/1000;
			this.addTerm(x.getFirst(), y1, orientation);
		} else if (orientation==3) {
			double x1 = (double)Math.round((x.getFirst()+size)*1000)/1000;
			this.addTerm(x1, y.getFirst(), orientation);
		} else if (orientation==6) {
			double y1 = (double)Math.round((y.getFirst()-size)*1000)/1000;
			this.addTerm(x.getFirst(), y1, orientation);
		} else {
			double x1 = (double)Math.round((x.getFirst()-size)*1000)/1000;
			this.addTerm(x1, y.getFirst(), orientation);
		}

		this.removeLast();
	}
	
	@Override
	public void draw() {
		int orientation = this.orientation.getFirst();
		if (StdDraw.isKeyPressed(37)||StdDraw.isKeyPressed(65)) {
			if (orientation != 3)
				orientation = 9;
		}
		if (StdDraw.isKeyPressed(38)||StdDraw.isKeyPressed(87)) {
			if (orientation != 6)
				orientation = 12;
		}
		if (StdDraw.isKeyPressed(39)||StdDraw.isKeyPressed(68)) {
			if (orientation != 9)
				orientation = 3;
		}
		if (StdDraw.isKeyPressed(40)||StdDraw.isKeyPressed(83)) {
			if (orientation != 12)
				orientation = 6;
		}
		this.blockIterator(orientation);
		double size = 0.025/2.0;
		ListIterator<Double> liX = this.x.listIterator();
		ListIterator<Double> liY = this.y.listIterator();
		while (liX.hasNext()) {
			double sX = liX.next() + size;
			double sY = liY.next() + size;
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(sX, sY, size);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.line(sX+size, sY-size, sX+size, sY-size);
		}
	}

	@Override
	public boolean isDone() {
		return watchOut();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String oString = "[";
		for (int i=0; i<orientation.size(); i++) {
			if (orientation.get(i)==3)
				oString = oString + "R";
			if (orientation.get(i)==6)
				oString = oString + "D";
			if (orientation.get(i)==9)
				oString = oString + "L";
			if (orientation.get(i)==12)
				oString = oString + "U";
			if (i+1 == orientation.size())
				oString = oString + "]";
			else
				oString = oString + ", ";
		}

		return "SnakeInterfaced [x=" + x + ", y=" + y + ", orientation=" + oString + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnakeInterfaced other = (SnakeInterfaced) obj;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
}
