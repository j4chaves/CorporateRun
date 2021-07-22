package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tile extends Entity {
	
	private final boolean isWalkable;
	private final boolean isTransparent;
	
	public Tile(Rectangle rectangle, Texture texture, boolean isWalkable, boolean isTransparent) {
		super(rectangle, texture);
		this.isWalkable = isWalkable;
		this.isTransparent = isTransparent;
	}

	
}
