package utils;

import java.util.ArrayList;

public class CheckersAIReturn {
	private Vector2i from;
	private Vector2i to;
	private ArrayList<Vector2i> jumped;
	
	public CheckersAIReturn (Vector2i from, Vector2i to, ArrayList<Vector2i> jumped) {
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
	
	public ArrayList<Vector2i> getJumped () {
		return jumped;
	}
}
