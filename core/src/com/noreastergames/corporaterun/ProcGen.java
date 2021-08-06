package com.noreastergames.corporaterun;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SortedIntList;

public class ProcGen {

	public GameMap generateDungeon(int mapWidth, int mapHeight) {
		GameMap map = new GameMap(Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.MAP_MAX_CELLS_HORIZONTAL, Global.MAP_MAX_CELLS_VERTICAL);
		
		int numRooms = MathUtils.random(Global.MIN_ROOMS, Global.MAX_ROOMS);
		
		Array<RectangularRoom> rooms = new Array<>();
		int counter = 0;
		do {
			int roomWidth = MathUtils.random(Global.ROOM_MIN_SIZE, Global.ROOM_MAX_SIZE);
			int roomHeight = MathUtils.random(Global.ROOM_MIN_SIZE, Global.ROOM_MAX_SIZE);
			
			int roomX = MathUtils.random(0, Global.MAP_MAX_CELLS_HORIZONTAL - roomWidth - 1);
			int roomY = MathUtils.random(0, Global.MAP_MAX_CELLS_VERTICAL - roomWidth - 1);
			
			RectangularRoom newRoom = new RectangularRoom(roomX, roomY, roomWidth, roomHeight);
			
			if (rooms.size == 0) {
				rooms.add(newRoom);
				counter++;
			}
			
			boolean validRoom = true;
			for (RectangularRoom r : rooms) {
				if (r.intersects(newRoom)) {
					validRoom = false;
					break;
				}
			}
			
			if (validRoom) {
				rooms.add(newRoom);
				counter++;
			}
		} while (counter < numRooms);
		
		// Set the rooms to walkable tiles
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
		
		rooms.sort();
		
		// Create tunnel between the rooms - TODO break into its own function
		for (int i = 0; i < rooms.size; i++) {
			if (i == 0) {
				continue;
			}
			
			RectangularRoom startRoom = rooms.get(i);
			RectangularRoom endRoom = rooms.get(i - 1);
			
			GridPoint2 tunnelStart = new GridPoint2(startRoom.getCenterX() / 32, startRoom.getCenterY() / 32);
			GridPoint2 tunnelEnd = new GridPoint2(endRoom.getCenterX() / 32, endRoom.getCenterY() / 32);
			Bresenham2 tunnel = new Bresenham2();
			Array<GridPoint2> tunnelPoints = tunnel.line(tunnelStart, tunnelEnd);
			
			for (int index = 0; index < tunnelPoints.size; index++) {
				if (index == 0) {
					continue;
				}
				
				if (index + 1 <= tunnelPoints.size -1) {
					GridPoint2 point1 = tunnelPoints.get(index);
					GridPoint2 point2 = tunnelPoints.get(index + 1);
					
					if (point1.dst2(point2) > 1) {
						GridPoint2 newPoint = new GridPoint2(point1);
						if (point1.sub(point2).x < 0) {
							point1.set(newPoint);	// Reset the point in tunnelPoints array because somehow this affects it
							newPoint.x += 1;
						} else if (point1.sub(point2).y < 0) {
							point1.set(newPoint);	// Reset the point in tunnelPoints array because somehow this affects it
							newPoint.y += 1;
						}
						tunnelPoints.insert(index + 1, newPoint);
					}
				}
			}
			
			/** This block for generating 'L' shaped tunnel
			int cornerX, cornerY;
			if (MathUtils.randomBoolean()) {
				cornerX = tunnelStart.x;
				cornerY = tunnelEnd.y;
			} else {
				cornerX = tunnelEnd.x;
				cornerY = tunnelStart.y;
			}
			**/
			
			// TODO - NEED TO FIX TUNNELING TO ACCOUNT FOR SIZE 32 PIXEL TILES 
			for (GridPoint2 point : tunnelPoints) {
				Rectangle rect = new Rectangle(point.x * 32, point.y * 32, Global.CELL_WIDTH, Global.CELL_HEIGHT);
				GridPoint2 tileCoord = new GridPoint2(point.x, point.y);
				Texture texture = new Texture("grassTile.png");
				Tile tile = new Tile(rect, texture, tileCoord, true, false);
				map.setSpecificMapCell(tile);
			}
		}
		
		return map;
	}
}
