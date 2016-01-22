package lab10.snake;

import java.util.LinkedList;
import java.util.ListIterator;

import sedgewick.StdDraw;

public class SnakeInt implements Anim {

	LinkedList<Integer> x;
	LinkedList<Integer> y;
	LinkedList<Integer> orientation;
	private boolean isDead;
	// 		12 = up
	// 		3 = right
	// 		6 = down
	// 		9 = left
	// 		think of a clock-face!!

	/**
	 * Constructor -- creates empty lists
	 */
	public SnakeInt() {
		this.reset();
	}

	/**
	 * Setter
	 * @param x = block's x coordinate (corresponds to lower left-hand corner)
	 * @param y = '' but for y instead of x
	 * @param orientation of block
	 * @return
	 */
	public SnakeInt addTerm(int x, int y, int orientation) {
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
	public SnakeInt addLast() {
		int orientation = this.orientation.getLast();
		int x = this.x.getLast();
		int y = this.y.getLast();
		int size = 1;
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
	public SnakeInt replaceTerm(int x, int y, int orientation, int index) {
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
	public SnakeInt removeLast() {
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
	public SnakeInt removeTerm(int index) {
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
		ListIterator<Integer> liX = this.x.listIterator();
		ListIterator<Integer> liY = this.y.listIterator();
		int x = this.x.getFirst();
		int y = this.y.getFirst();
		liX.next();
		liY.next();
		while (liX.hasNext() && liY.hasNext()) {
			int nextX = liX.next();
			int nextY = liY.next();
			if ((nextX == x) && (nextY == y)) {
				System.out.println("watchout head = [" + x + "," + y + "], overlapped point = [" + nextX + "," + nextY + "]");
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
		if ((this.x.getFirst() == 40) || 
				(this.y.getFirst() == 40)) {
			System.out.println("wall");
			return true;
		}
		else if((this.x.getFirst() == -1) || 
				(this.y.getFirst() == -1)) {
			System.out.println("wall2");
			return true;
		}
		else
			return false;
	}

	/**
	 * initializes the game with (numBlocks) number of blocks in the orientation specified
	 * @param orientation
	 */
	public void initializer(int orientation) {
		int numBlocks = 1;
		int size = 1;
		double randomX = Math.random();
		double randomY = Math.random();
		int x = (int)(randomX*40);
		x = x/size;
		int y = (int)(randomY*40);
		y = y/size;
		if ((orientation==12) && (y > 35)) {
			this.initializer(orientation);
			return;
		}
		if ((orientation==3) && (x > 35)) {
			this.initializer(orientation);
			return;
		}
		if ((orientation==6) && (y < 6)) {
			this.initializer(orientation);
			return;
		}
		if ((orientation==9) && (x < 6)) {
			this.initializer(orientation);
			return;
		}
		for (int i=0; i<numBlocks; i++) {
			size = (size*i);
			if (orientation==12)
				this.addTerm(x, y+size, orientation);
			else if (orientation==3)
				this.addTerm(x+size, y, orientation);
			else if (orientation==6)
				this.addTerm(x, y-size, orientation);
			else
				this.addTerm(x-size, y, orientation);
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
		int size = 1;
		if (orientation==12) {
			int y1 = y.getFirst()+size;
			this.addTerm(x.getFirst(), y1, orientation);
		} else if (orientation==3) {
			int x1 = x.getFirst()+size;
			this.addTerm(x1, y.getFirst(), orientation);
		} else if (orientation==6) {
			int y1 = y.getFirst()-size;
			this.addTerm(x.getFirst(), y1, orientation);
		} else {
			int x1 = x.getFirst()-size;
			this.addTerm(x1, y.getFirst(), orientation);
		}

		this.removeLast();
	}

	@Override
	public void draw() {
		int orientation = this.orientation.getFirst();
		if (StdDraw.isKeyPressed(37)||StdDraw.isKeyPressed(65)) {
			if ((orientation != 3) || (this.x.size() < 1)) {
				orientation = 9;
			}
		}
		if (StdDraw.isKeyPressed(38)||StdDraw.isKeyPressed(87)) {
			if ((orientation != 6) || (this.x.size() < 1)) {
				orientation = 12;
			}			
		}
		if (StdDraw.isKeyPressed(39)||StdDraw.isKeyPressed(68)) {
			if ((orientation != 9) || (this.x.size() < 1))
				orientation = 3;
		}
		if (StdDraw.isKeyPressed(40)||StdDraw.isKeyPressed(83)) {
			if ((orientation != 12) || (this.x.size() < 1))
				orientation = 6;
		}
		this.blockIterator(orientation);
		double size = 0.025/2.0;
		ListIterator<Integer> liX = this.x.listIterator();
		ListIterator<Integer> liY = this.y.listIterator();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.075, 0.015, "Score: " + (this.x.size()-1));
		while (liX.hasNext()) {
			double sX = (liX.next()/40.0);
			sX = sX + size;
			double sY = (liY.next()/40.0);
			sY = sY + size;
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledSquare(sX, sY, size);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.line(sX+size, sY-size, sX+size, sY-size);
		}
	}

	@Override
	public boolean isDone() {
		if (watchOut() || atWall())
			this.isDead = true;
		return this.isDead;
	}

	@Override
	public void reset() {
		this.x = new LinkedList<Integer>();
		this.y = new LinkedList<Integer>();
		this.orientation = new LinkedList<Integer>();
		int orientation = this.orientationScrambler();
		this.initializer(orientation);
		this.isDead = false;
	}

	@Override
	public String toString() {
		return "SnakeInt [x=" + x + ", y=" + y + ", orientation=" + orientation + ", isDead=" + isDead + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDead ? 1231 : 1237);
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnakeInt other = (SnakeInt) obj;
		if (isDead != other.isDead)
			return false;
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
