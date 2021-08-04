package com.noreastergames.corporaterun;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameMap {

	private HashMap<GridPoint2, Tile> mapCells;
	
	private final int CELL_HEIGHT;
	private final int CELL_WIDTH;
	private final int MAP_MAX_CELLS_HORIZONTAL;
	private final int MAP_MAX_CELLS_VERTICAL;
	
	public GameMap(int cellHeight, int cellWidth, int maxCellsHoriz, int maxCellsVert) {
		this.CELL_HEIGHT = cellHeight;
		this.CELL_WIDTH = cellWidth;
		this.MAP_MAX_CELLS_HORIZONTAL = maxCellsHoriz;
		this.MAP_MAX_CELLS_VERTICAL = maxCellsVert;
		
		this.mapCells = generateMap();
	}
	
	public HashMap<GridPoint2, Tile> getMapCells() {
		return mapCells;
	}

	public void setMapCells(HashMap<GridPoint2, Tile> mapCells) {
		this.mapCells = mapCells;
	}

	public int getCellHeight() {
		return CELL_HEIGHT;
	}

	public int getCellWidth() {
		return CELL_WIDTH;
	}

	public int getMapMaxCellsHorizontal() {
		return MAP_MAX_CELLS_HORIZONTAL;
	}

	public int getMapMaxCellsVertical() {
		return MAP_MAX_CELLS_VERTICAL;
	}
	
	public void setSpecificMapCell(Tile tile) {
		if (mapCells.containsKey(tile.getGridPoint2())) {
			mapCells.replace(tile.getGridPoint2(), tile);
		}
	}

	private HashMap<GridPoint2, Tile> generateMap() {
		HashMap<GridPoint2, Tile> mapCells = new HashMap<>();
		for (int i = 0; i < MAP_MAX_CELLS_HORIZONTAL; i++) {
			for (int j = 0; j < MAP_MAX_CELLS_VERTICAL; j++) {
				Rectangle cellRect = new Rectangle();
				cellRect.height = CELL_HEIGHT;
				cellRect.width = CELL_WIDTH;
				cellRect.x = CELL_WIDTH * i;
				cellRect.y = CELL_HEIGHT * j;
				
				/*int randTile = MathUtils.random(0, 2);
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
				}*/
				
				String cellTextureName = "brickTile.png";
				
				Texture cellTexture = new Texture(cellTextureName);
				GridPoint2 tileCoord = new GridPoint2(i, j);
				Tile tile = new Tile(cellRect, cellTexture, tileCoord, false, false);
				//Tile tile = new Tile(cellRect, cellTexture, tileCoord, isWalkable, isTransparent);
				mapCells.put(tileCoord, tile);
			}
		}
		return mapCells;
	}

	public Array<Tile> getWalkableCells() {
		Array<Tile> walkableCells = new Array<>();
		
		for (Tile t : mapCells.values()) {
			if (t.isWalkable()) {
				walkableCells.add(t);
			}
		}
		
		return walkableCells;
	}
}
