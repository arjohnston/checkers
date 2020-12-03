package utils;

public class CheckersAIReturn {
	private Vector2i from;
	private Vector2i to;
	private Vector2i[] jumped;
	
	public CheckersAIReturn (Vector2i from, Vector2i to, Vector2i[] jumped) {
		this.from = from;
		this.to = to;
		this.jumped = jumped;
	}
	
	public Vector2i getFrom () {
		return this.from;
	}
	
	public Vector2i getTo () {
		return this.to;
	}
	
	public Vector2i[] getJumped () {
		return jumped;
	}
}
