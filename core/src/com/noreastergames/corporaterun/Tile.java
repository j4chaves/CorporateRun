package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile extends Entity {
	
	private final boolean isWalkable;
	private final boolean isTransparent;
	private final int xTilePosition;
	private final int yTilePosition;
	private boolean inFieldOfView;
	
	public Tile(Rectangle rectangle, Texture texture, boolean isWalkable, boolean isTransparent,
			int xTilePosition, int yTilePosition) {
		super(rectangle, texture);
		this.isWalkable = isWalkable;
		this.isTransparent = isTransparent;
		this.xTilePosition = xTilePosition;
		this.yTilePosition = yTilePosition;
	}

	
}
