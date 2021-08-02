package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ProcGen {

	public GameMap generateDungeon(int mapWidth, int mapHeight) {
		GameMap map = new GameMap(Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.MAP_MAX_CELLS_HORIZONTAL, Global.MAP_MAX_CELLS_VERTICAL);
		
		RectangularRoom room1 = new RectangularRoom(8, 8, 4, 4);
		RectangularRoom room2 = new RectangularRoom(16, 8, 4, 4);
		
		Array<RectangularRoom> rooms = new Array<>();
		rooms.add(room1, room2);
		
		//HashMap mapCells = map.getMapCells()
		for (RectangularRoom r : rooms) {
			for (int i = r.getBottomLeftX() / 32; i < r.getTopRightX() / 32; i++) {
				for (int j = r.getBottomLeftY() / 32; j < r.getTopRightY() / 32; j++) {
					Rectangle rect = new Rectangle(i * 32, j * 32, Global.CELL_WIDTH, Global.CELL_HEIGHT);
					TileCoord tileCoord = new TileCoord(i, j);
					Texture texture = new Texture("grassTile.png");
					Tile tile = new Tile(rect, texture, tileCoord, true, false);
					map.setSpecificMapCell(tile);
				}
			}
		}
		
		return map;
	}
}
