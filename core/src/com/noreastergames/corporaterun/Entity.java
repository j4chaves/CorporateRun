package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	
	private Rectangle rectangle;
	private Texture texture;
	
	private int xTilePosition;
	private int yTilePosition;

	public Entity(Rectangle rectangle, Texture texture, int xTilePosition, int yTilePosition) {
		this.rectangle = rectangle;
		this.texture = texture;
		this.xTilePosition = xTilePosition;
		this.yTilePosition = yTilePosition;
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
	
	public int getxTilePosition() {
		return xTilePosition;
	}


	public void setxTilePosition(int xTilePosition) {
		this.xTilePosition = xTilePosition;
	}


	public int getyTilePosition() {
		return yTilePosition;
	}


	public void setyTilePosition(int yTilePosition) {
		this.yTilePosition = yTilePosition;
	}


	public void moveEntity(Action action, Tile tile) {
		if (Action.MOVE_UP.equals(action)) {
			if (this.yTilePosition + 1 < GameMap.getMapMaxCellsVertical() && tile.isWalkable()) {
				this.getRectangle().y += 64;
				this.yTilePosition += 1;
			}
		} else if (Action.MOVE_DOWN.equals(action)) {
			if (this.yTilePosition -1 > -1 && tile.isWalkable()) {
				this.getRectangle().y -= 64;
				this.yTilePosition -= 1;
			}
		} else if (Action.MOVE_LEFT.equals(action)) {
			if (this.xTilePosition - 1 > -1 && tile.isWalkable()) {
				this.getRectangle().x -= 64;
				this.xTilePosition -= 1;
			}
		} else if (Action.MOVE_RIGHT.equals(action)) {
			if (this.xTilePosition + 1 < GameMap.getMapMaxCellsHorizontal() && tile.isWalkable()) {
				this.getRectangle().x += 64;
				this.xTilePosition += 1;
			}
		}
	}
	
	private boolean isValidMovement() {
		return false;
	}
	
}
