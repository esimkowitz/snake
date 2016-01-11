package lab10.snake;

import sedgewick.StdDraw;

public class Food implements Anim{
	
	private Double x;
	private Double y;
	private boolean isDone;
	
	public Food() {
		this.setNew();
	}
	
	public void setNew() {
		double size = .025;
		double randomX = Math.random();
		double randomY = Math.random();
		randomX = (double)Math.round(randomX/size);
		randomY = (double)Math.round(randomY/size);
		randomX = (double)Math.round((randomX*size)*1000)/1000;
		randomY = (double)Math.round((randomY*size)*1000)/1000;
		this.x = randomX;
		this.y = randomY;
		if ((this.x >= 1.0 || this.x<0) || (this.y >= 1.0 || this.y < 0)) {
			this.setNew();
		}
		this.isDone = false;
	}
	
	@Override
	public void draw() {
		double size = 0.025/2.0;
		StdDraw.setPenColor(StdDraw.RED);
		double fx = this.getX() + size;
		double fy = this.getY() + size;
		StdDraw.filledSquare(fx, fy, size);
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
	public Double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public Double getY() {
		return y;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Food [x=" + x + ", y=" + y + "]";
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
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
