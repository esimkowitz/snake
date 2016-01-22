package lab10.snake;

import sedgewick.StdDraw;

public class FoodInt implements Anim {

	private Integer x;
	private Integer y;
	private boolean isDone;
	
	public FoodInt() {
		this.setNew();
	}
	
	public void setNew() {
		double randomX = Math.random();
		double randomY = Math.random();
		int x = (int)(randomX*40);
		int y = (int)(randomY*40);
		this.x = x;
		this.y = y;
		this.isDone = false;
	}
	
	@Override
	public void draw() {
		double size = 0.0125;
		StdDraw.setPenColor(StdDraw.RED);
		double fX = ((double)this.x/40.0);
		fX = fX + size;
		double fY = ((double)this.y/40.0);
		fY = fY + size;
		StdDraw.filledSquare(fX, fY, size);
	}

	@Override
	public boolean isDone() {
		return this.isDone;
	}
	
	@Override
	public void reset() {
		this.setNew();	
	}

	/**
	 * @return the x
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}

	@Override
	public String toString() {
		return "FoodInt [x=" + x + ", y=" + y + ", isDone=" + isDone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDone ? 1231 : 1237);
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
		FoodInt other = (FoodInt) obj;
		if (isDone != other.isDone)
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
