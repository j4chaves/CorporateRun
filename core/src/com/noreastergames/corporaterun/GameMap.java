package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameMap {

	private Array<Tile> mapCells;
	
	private static final int CELL_HEIGHT = 64;
	private static final int CELL_WIDTH = 64;
	private static final int MAP_MAX_CELLS_HORIZONTAL = 12;
	private static final int MAP_MAX_CELLS_VERTICAL = 9;
	
	public GameMap() {
		this.mapCells = generateMap();
	}
	
	public Array<Tile> getMapCells() {
		return mapCells;
	}

	public void setMapCells(Array<Tile> mapCells) {
		this.mapCells = mapCells;
	}

	public static int getCellHeight() {
		return CELL_HEIGHT;
	}

	public static int getCellWidth() {
		return CELL_WIDTH;
	}

	public static int getMapMaxCellsHorizontal() {
		return MAP_MAX_CELLS_HORIZONTAL;
	}

	public static int getMapMaxCellsVertical() {
		return MAP_MAX_CELLS_VERTICAL;
	}

	private Array<Tile> generateMap() {
		Array<Tile> mapCells = new Array<>();
		for (int i = 0; i < MAP_MAX_CELLS_HORIZONTAL; i++) {
			for (int j = 0; j < MAP_MAX_CELLS_VERTICAL; j++) {
				Rectangle cellRect = new Rectangle();
				cellRect.height = CELL_HEIGHT;
				cellRect.width = CELL_WIDTH;
				cellRect.x = CELL_WIDTH * i;
				cellRect.y = CELL_HEIGHT * j;
				int randTile = MathUtils.random(0, 2);
				String cellTextureName = new String();
				boolean isWalkable, isTransparent;
				if (randTile == 0) {
					cellTextureName = "grassTile.png";
					isWalkable = true;
					isTransparent = true;
				} else if (randTile == 1) {
					cellTextureName = "waterTile.png";
					isWalkable = false;
					isTransparent = true;
				} else {
					cellTextureName = "brickTile.png";
					isWalkable = false;
					isTransparent = false;
				}
				
				Texture cellTexture = new Texture(cellTextureName);
				Tile tile = new Tile(cellRect, cellTexture, isWalkable, isTransparent, i, j);
				mapCells.add(tile);
				System.out.println("Tile x: " + i + "  Tile y: " + j);
			}
		}
		return mapCells;
	}
}
