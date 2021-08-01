package com.noreastergames.corporaterun;

import java.util.Objects;

public class TileCoord {

	private int xCoord;
	private int yCoord;
	
	public TileCoord(int xCoord, int yCoord) {
		this.yCoord = yCoord;
		this.xCoord = xCoord;
	}
	
	public int getyCoord() {
		return yCoord;
	}
	
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public int getxCoord() {
		return xCoord;
	}
	
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(xCoord, yCoord);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TileCoord other = (TileCoord) obj;
		return xCoord == other.xCoord && yCoord == other.yCoord;
	}
	
}
