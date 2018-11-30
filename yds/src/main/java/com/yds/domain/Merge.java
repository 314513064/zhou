package com.yds.domain;

import java.util.List;

public class Merge {
	private String code;
	private double direction;

	private List<Position> positions;

	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction2) {
		this.direction = direction2;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	
	
}
