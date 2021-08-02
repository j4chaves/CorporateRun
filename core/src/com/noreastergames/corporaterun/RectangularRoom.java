package com.noreastergames.corporaterun;

public class RectangularRoom {

	private int bottomLeftX;
	private int bottomLeftY;
	private int topRightX;
	private int topRightY;
	private int centerX;
	private int centerY;
	private int width;
	private int height;
	
	public RectangularRoom(int bottomLeftX, int bottomLeftY, int width, int height) {
		this.bottomLeftX = bottomLeftX * 32;
		this.bottomLeftY = bottomLeftY * 32;
		this.width = width * 32;
		this.height = height * 32;
		
		this.topRightX = this.bottomLeftX + this.width;
		this.topRightY = this.bottomLeftY + this.height;
		
		this.centerX = (this.bottomLeftX + topRightX) / 2;
		this.centerY = (this.bottomLeftY + topRightY) / 2;
	}
	
	public int[][] getInner() {
		int[][] array = {{bottomLeftX + 1, bottomLeftY + 1},{topRightX, topRightY}};
		return array; 
	}

	public int getBottomLeftX() {
		return bottomLeftX;
	}

	public void setBottomLeftX(int bottomLeftX) {
		this.bottomLeftX = bottomLeftX;
	}

	public int getBottomLeftY() {
		return bottomLeftY;
	}

	public void setBottomLeftY(int bottomLeftY) {
		this.bottomLeftY = bottomLeftY;
	}

	public int getTopRightX() {
		return topRightX;
	}

	public void setTopRightX(int topRightX) {
		this.topRightX = topRightX;
	}

	public int getTopRightY() {
		return topRightY;
	}

	public void setTopRightY(int topRightY) {
		this.topRightY = topRightY;
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
