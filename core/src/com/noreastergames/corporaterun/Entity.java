package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	
	private Rectangle rectangle;
	private Texture texture;
	
	private TileCoord tileCoord;

	public Entity(Rectangle rectangle, Texture texture, TileCoord tileCoord) {
		this.rectangle = rectangle;
		this.texture = texture;
		this.tileCoord = tileCoord;
	}


	public Rectangle getRectangle() {
		return rectangle;
	}


	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}


	public Texture getTexture() {
		return texture;
	}


	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public TileCoord getTileCoord() {
		return tileCoord;
	}


	public void setTileCoord(TileCoord tileCoord) {
		this.tileCoord = tileCoord;
	}

	public void moveEntity(Action action, Tile tile) {
		if (Action.MOVE_UP.equals(action)) {
			if (this.tileCoord.getYCoord() + 1 < Global.MAP_MAX_CELLS_VERTICAL && tile.isWalkable()) {
				this.getRectangle().y += Global.CELL_HEIGHT;
				this.tileCoord.setYCoord(this.tileCoord.getYCoord() + 1);
			}
		} else if (Action.MOVE_DOWN.equals(action)) {
			if (this.tileCoord.getYCoord() -1 > -1 && tile.isWalkable()) {
				this.getRectangle().y -= Global.CELL_HEIGHT;
				this.tileCoord.setYCoord(this.tileCoord.getYCoord() - 1);
			}
		} else if (Action.MOVE_LEFT.equals(action)) {
			if (this.tileCoord.getXCoord() - 1 > -1 && tile.isWalkable()) {
				this.getRectangle().x -= Global.CELL_WIDTH;
				this.tileCoord.setXCoord(this.tileCoord.getXCoord() - 1);
			}
		} else if (Action.MOVE_RIGHT.equals(action)) {
			if (this.tileCoord.getXCoord() + 1 < Global.MAP_MAX_CELLS_HORIZONTAL && tile.isWalkable()) {
				this.getRectangle().x += Global.CELL_WIDTH;
				this.tileCoord.setXCoord(this.tileCoord.getXCoord() + 1);
			}
		}
	}
	
	private boolean isValidMovement() {
		return false;
	}
	
}
