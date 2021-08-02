package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile extends Entity {
	
	private final boolean isWalkable;
	private final boolean isTransparent;	
	private boolean inFieldOfView;
	private boolean isOccupied;
	
	public Tile(Rectangle rectangle, Texture texture, TileCoord tileCoord, 
			boolean isWalkable, boolean isTransparent) {
		super(rectangle, texture, tileCoord);
		this.isWalkable = isWalkable;
		this.isTransparent = isTransparent;
	}

	public boolean isInFieldOfView() {
		return inFieldOfView;
	}

	public void setInFieldOfView(boolean inFieldOfView) {
		this.inFieldOfView = inFieldOfView;
	}

	public boolean isWalkable() {
		return isWalkable;
	}

	public boolean isTransparent() {
		return isTransparent;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
}
