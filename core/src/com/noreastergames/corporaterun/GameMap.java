package com.noreastergames.corporaterun;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameMap {

	private HashMap<GridPoint2, Tile> mapCells;
	private Array<Tile> visible;
	private Array<Tile> explored;
	
	private final int CELL_HEIGHT;
	private final int CELL_WIDTH;
	private final int MAP_MAX_CELLS_HORIZONTAL;
	private final int MAP_MAX_CELLS_VERTICAL;
	
	public GameMap(int cellHeight, int cellWidth, int maxCellsHoriz, int maxCellsVert) {
		this.CELL_HEIGHT = cellHeight;
		this.CELL_WIDTH = cellWidth;
		this.MAP_MAX_CELLS_HORIZONTAL = maxCellsHoriz;
		this.MAP_MAX_CELLS_VERTICAL = maxCellsVert;
		
		this.visible = new Array<>();
		this.explored = new Array<>();
		
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
				
				String cellTextureName = "brickTile.png";
				
				Texture cellTexture = new Texture(cellTextureName);
				GridPoint2 tileCoord = new GridPoint2(i, j);
				Tile tile = new Tile(cellRect, cellTexture, tileCoord, false, false);
				tile.setTileType(TileType.GRASS);
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
	
	public void updateFOV(Entity playerTile) {
		int radius = 4;
		GridPoint2 playerCoord = playerTile.getGridPoint2();
		
		for (Map.Entry<GridPoint2, Tile> entry : mapCells.entrySet()) {
			Tile tile = entry.getValue();
			if (tile.getGridPoint2().dst(playerCoord) < radius) {
				tile.setExplored(true);
				tile.setInFieldOfView(true);
				if (tile.getTileType().equals(TileType.BRICK)) {
					tile.setTexture(new Texture("grassTile.png"));
				} else if (tile.getTileType().equals(TileType.GRASS)) {
					tile.setTexture(new Texture("brickTile.png"));
				}
				explored.add(tile);
				visible.add(tile);
			} else {
				if (visible.contains(tile, false)) {
					visible.removeValue(tile, false);
					
					if (tile.getTileType().equals(TileType.BRICK)) {
						tile.setTexture(new Texture("grassTileOutsideFOV.png"));
					} else if (tile.getTileType().equals(TileType.GRASS)) {
						tile.setTexture(new Texture("brickTileOutsideFOV.png"));
					}
				}
			}
		}
	}
	
	public Optional<Entity> getBlockingEntityAtLocation(Array<Entity> entities, GridPoint2 point) {
		for (Entity e : entities) {
			if (e.isBlocksMovement() && e.getGridPoint2().equals(point)) {
				return Optional.of(e);
			}
		}
		
		return Optional.empty();
	}
}
