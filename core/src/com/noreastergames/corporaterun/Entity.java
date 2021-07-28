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
			if (this.tileCoord.getRow() + 1 < GameMap.getMapMaxCellsVertical() && tile.isWalkable()) {
				this.getRectangle().y += 64;
				this.tileCoord.setRow(this.tileCoord.getRow() + 1);
			}
		} else if (Action.MOVE_DOWN.equals(action)) {
			if (this.tileCoord.getRow() -1 > -1 && tile.isWalkable()) {
				this.getRectangle().y -= 64;
				this.tileCoord.setRow(this.tileCoord.getRow() - 1);
			}
		} else if (Action.MOVE_LEFT.equals(action)) {
			if (this.tileCoord.getColumn() - 1 > -1 && tile.isWalkable()) {
				this.getRectangle().x -= 64;
				this.tileCoord.setColumn(this.tileCoord.getColumn() - 1);
			}
		} else if (Action.MOVE_RIGHT.equals(action)) {
			if (this.tileCoord.getColumn() + 1 < GameMap.getMapMaxCellsHorizontal() && tile.isWalkable()) {
				this.getRectangle().x += 64;
				this.tileCoord.setColumn(this.tileCoord.getColumn() + 1);
			}
		}
	}
	
	private boolean isValidMovement() {
		return false;
	}
	
}
