package com.noreastergames.corporaterun.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.noreastergames.corporaterun.Action;
import com.noreastergames.corporaterun.Global;
import com.noreastergames.corporaterun.Tile;

public class Entity {
	
	private Rectangle rectangle;
	private Texture texture;
	
	private GridPoint2 gridPoint;
	
	private boolean blocksMovement;
	private boolean isVisible;
	

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

	public boolean isBlocksMovement() {
		return blocksMovement;
	}

	public void setBlocksMovement(boolean blocksMovement) {
		this.blocksMovement = blocksMovement;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
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
}
