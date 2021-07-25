package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile extends Entity {
	
	private final boolean isWalkable;
	private final boolean isTransparent;	
	private boolean inFieldOfView;
	
	public Tile(Rectangle rectangle, Texture texture, boolean isWalkable, boolean isTransparent,
			int xTilePosition, int yTilePosition) {
		super(rectangle, texture, xTilePosition, yTilePosition);
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
}
