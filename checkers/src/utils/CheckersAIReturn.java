package utils;

public class CheckersAIReturn {
	private Vector2i from;
	private Vector2i to;
	
	public CheckersAIReturn (Vector2i from, Vector2i to) {
		this.from = from;
		this.to = to;
	}
	
	public Vector2i getFrom () {
		return this.from;
	}
	
	public Vector2i getTo () {
		return this.to;
	}
}
