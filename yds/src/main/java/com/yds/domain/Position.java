package com.yds.domain;

public class Position {
	private int x;
	private int y;
	private int is_chosen;

	public Position(Position aa) {
		this.x = aa.getX();
		this.y = aa.getY();
		this.is_chosen = 0;
	}
	
	public Position(int x,int y,int is_chosen) {
		this.x = x;
		this.y = y;
		this.is_chosen = is_chosen;
	}
	
	public int getIs_chosen() {
		return is_chosen;
	}

	public void setIs_chosen(int is_chosen) {
		this.is_chosen = is_chosen;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
