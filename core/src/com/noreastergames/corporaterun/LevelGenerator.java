package com.noreastergames.corporaterun;

public class LevelGenerator {

	private int topLeftX;
	private int topLeftY;
	private int bottomRightX;
	private int bottomRightY;
	private int centerX;
	private int centerY;
	private int width;
	private int height;
	
	public LevelGenerator(int topLeftX, int topLeftY, int width, int height) {
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.width = width;
		this.height = height;
		
		this.bottomRightX = topLeftX + width;
		this.bottomRightY = topLeftY + height;
		
		this.centerX = (topLeftX + bottomRightX) / 2;
		this.centerY = (topLeftY + bottomRightY) / 2;
	}
	
	public int[][] getInner() {
		int[][] array = {{topLeftX + 1, topLeftY + 1},{bottomRightX, bottomRightY}};
		return array; 
	}

	public int getTopLeftX() {
		return topLeftX;
	}

	public void setTopLeftX(int topLeftX) {
		this.topLeftX = topLeftX;
	}

	public int getTopLeftY() {
		return topLeftY;
	}

	public void setTopLeftY(int topLeftY) {
		this.topLeftY = topLeftY;
	}

	public int getBottomRightX() {
		return bottomRightX;
	}

	public void setBottomRightX(int bottomRightX) {
		this.bottomRightX = bottomRightX;
	}

	public int getBottomRightY() {
		return bottomRightY;
	}

	public void setBottomRightY(int bottomRightY) {
		this.bottomRightY = bottomRightY;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
