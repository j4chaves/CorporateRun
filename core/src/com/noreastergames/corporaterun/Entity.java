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


	public void moveEntity(Action action, int newXTilePosition, int newYTilePosition) {
		if (Action.MOVE_UP.equals(action)) {
			if (newYTilePosition < GameMap.getMapMaxCellsVertical()) {
				this.getRectangle().y += 64;
				this.yTilePosition = newYTilePosition;
			}
		} else if (Action.MOVE_DOWN.equals(action)) {
			if (newYTilePosition > -1) {
				this.getRectangle().y -= 64;
				this.yTilePosition = newYTilePosition;
			}
		} else if (Action.MOVE_LEFT.equals(action)) {
			if (newXTilePosition > -1) {
				this.getRectangle().x -= 64;
				this.xTilePosition = newXTilePosition;
				System.out.println(newXTilePosition);
			}
		} else if (Action.MOVE_RIGHT.equals(action)) {
			if (newXTilePosition < GameMap.getMapMaxCellsHorizontal()) {
				this.getRectangle().x += 64;
				this.xTilePosition = newXTilePosition;
			}
		}
	}
	
	private boolean isValidMovement() {
		return false;
	}
	
}
