package utils;

/**
 * Utility class to represent a x, y coordinate on the game board.
 * 
 * @author Andrew Johnston
 *
 */

public class Vector2i {
	public Integer x;
	public Integer y;

	/**
	 * Initialize with a x,y coordinate
	 * @param x Integer
	 * @param y Integer
	 */
	public Vector2i (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString () {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public boolean equals(Vector2i vec2) {
        return this.x == vec2.x && this.y == vec2.y;
	}
}