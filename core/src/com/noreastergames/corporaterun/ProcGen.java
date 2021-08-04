package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class ProcGen {

	public GameMap generateDungeon(int mapWidth, int mapHeight) {
		GameMap map = new GameMap(Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.MAP_MAX_CELLS_HORIZONTAL, Global.MAP_MAX_CELLS_VERTICAL);
		
		RectangularRoom room1 = new RectangularRoom(2, 2, 4, 4);
		RectangularRoom room2 = new RectangularRoom(12, 2, 4, 4);
		
		Array<RectangularRoom> rooms = new Array<>();
		rooms.add(room1, room2);
		
		//HashMap mapCells = map.getMapCells()
		for (RectangularRoom r : rooms) {
			for (int i = r.getBottomLeftX() / 32; i < r.getTopRightX() / 32; i++) {
				for (int j = r.getBottomLeftY() / 32; j < r.getTopRightY() / 32; j++) {
					Rectangle rect = new Rectangle(i * 32, j * 32, Global.CELL_WIDTH, Global.CELL_HEIGHT);
					GridPoint2 tileCoord = new GridPoint2(i, j);
					Texture texture = new Texture("grassTile.png");
					Tile tile = new Tile(rect, texture, tileCoord, true, false);
					map.setSpecificMapCell(tile);
				}
			}
		}
		
		// Create tunnel between the rooms
		GridPoint2 room1TunnelStart = new GridPoint2(room1.getCenterX() / 32, room1.getCenterY() / 32);
		GridPoint2 room2TunnelStart = new GridPoint2(room2.getCenterX() / 32, room2.getCenterY() / 32);
		Bresenham2 tunnel = new Bresenham2();
		Array<GridPoint2> tunnelPoints = tunnel.line(room1TunnelStart, room2TunnelStart);
		
		for (GridPoint2 point : tunnelPoints) {
			Rectangle rect = new Rectangle(point.x * 32, point.y * 32, Global.CELL_WIDTH, Global.CELL_HEIGHT);
			GridPoint2 tileCoord = new GridPoint2(point.x, point.y);
			Texture texture = new Texture("grassTile.png");
			Tile tile = new Tile(rect, texture, tileCoord, true, false);
			map.setSpecificMapCell(tile);
		}
		
		return map;
	}
}
