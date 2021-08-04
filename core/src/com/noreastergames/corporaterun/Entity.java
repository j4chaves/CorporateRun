package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
	
	private Rectangle rectangle;
	private Texture texture;
	
	private GridPoint2 gridPoint;

	public Entity(Rectangle rectangle, Texture texture, GridPoint2 gridPoint) {
		this.rectangle = rectangle;
		this.texture = texture;
		this.gridPoint = gridPoint;
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
	
	public GridPoint2 getGridPoint2() {
		return gridPoint;
	}


	public void setGridPoint2(GridPoint2 gridPoint) {
		this.gridPoint = gridPoint;
	}

	public void moveEntity(Action action, Tile tile) {
		if (Action.MOVE_UP.equals(action)) {
			if (gridPoint.y + 1 < Global.MAP_MAX_CELLS_VERTICAL && tile.isWalkable()) {
				getRectangle().y += Global.CELL_HEIGHT;
				gridPoint.set(gridPoint.x, gridPoint.y + 1);
			}
		} else if (Action.MOVE_DOWN.equals(action)) {
			if (gridPoint.y - 1 > -1 && tile.isWalkable()) {
				getRectangle().y -= Global.CELL_HEIGHT;
				gridPoint.set(gridPoint.x, gridPoint.y - 1);
			}
		} else if (Action.MOVE_LEFT.equals(action)) {
			if (gridPoint.x - 1 > -1 && tile.isWalkable()) {
				getRectangle().x -= Global.CELL_WIDTH;
				gridPoint.set(gridPoint.x - 1, gridPoint.y);
			}
		} else if (Action.MOVE_RIGHT.equals(action)) {
			if (gridPoint.x + 1 < Global.MAP_MAX_CELLS_HORIZONTAL && tile.isWalkable()) {
				getRectangle().x += Global.CELL_WIDTH;
				gridPoint.set(gridPoint.x + 1, gridPoint.y);
			}
		}
	}
	
	private boolean isValidMovement() {
		return false;
	}
	
}
