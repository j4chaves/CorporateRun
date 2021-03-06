package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.noreastergames.corporaterun.entity.Entity;

public class Tile extends Entity {
	
	private final boolean isWalkable;
	private final boolean isTransparent;	// True if the tile doesn't block player's Field of View
	private boolean inFieldOfView;
	private boolean isOccupied;
	
	private TileType tileType;
	
	public Tile(Rectangle rectangle, Texture texture, GridPoint2 gridPoint, 
			boolean isWalkable, boolean isTransparent) {
		super(rectangle, texture, gridPoint);
		this.isWalkable = isWalkable;
		this.isTransparent = isTransparent;
		tileType = TileType.BRICK;
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

	public TileType getTileType() {
		return tileType;
	}

	public void setTileType(TileType tileType) {
		this.tileType = tileType;
	}
}
