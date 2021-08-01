package com.noreastergames.corporaterun;

import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class CorporateRunGame extends ApplicationAdapter {
	private static final int SCREEN_HEIGHT = 1024;
	private static final int SCREEN_WIDTH = 1280;
	
	private static final int DEFAULT_IMAGE_HEIGHT = 64;
	private static final int DEFAULT_IMAGE_WIDTH = 64;
	
	private Texture playerImage;
	private Entity player;
	
	private Texture enemyImage;
	private Array<Entity> enemies;
	private long enemySpawnTime;
	
	private GameMap gameMap;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		// Load images for player + enemy, 64x64 pixels
		playerImage = new Texture(Gdx.files.internal("player.png"));
		enemyImage = new Texture(Gdx.files.internal("enemy.png"));
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		batch = new SpriteBatch();
		
		enemies = new Array<>();
		
		Rectangle playerRectangle = new Rectangle(0, 0, 64, 64);
		player = new Entity(playerRectangle, playerImage, new TileCoord(0, 0));
		
		gameMap = new GameMap(Global.CELL_HEIGHT, Global.CELL_WIDTH, Global.MAP_MAX_CELLS_HORIZONTAL, Global.MAP_MAX_CELLS_VERTICAL);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.DARK_GRAY);
		camera.update();
		batch.begin();
		
		for (Map.Entry<TileCoord, Tile> entry : gameMap.getMapCells().entrySet()) {
			Tile mapCell = entry.getValue();
			batch.draw(mapCell.getTexture(), mapCell.getRectangle().x, mapCell.getRectangle().y);
		}
		
		batch.draw(player.getTexture(), player.getRectangle().x, player.getRectangle().y);
		
		for (Entity enemy : enemies) {
			batch.draw(enemy.getTexture(), enemy.getRectangle().x, enemy.getRectangle().y);
			
			if (enemy.getRectangle().overlaps(player.getRectangle())) {
				enemies.removeValue(enemy, false);
			}
		}
		
		batch.end();
		
		
		/**
		 *  Keyboard Controls
		 */
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			int attemptedYMove = player.getTileCoord().getRow() + 1;
			Tile tile = gameMap.getMapCells().get(new TileCoord(attemptedYMove, player.getTileCoord().getColumn()));
			player.moveEntity(Action.MOVE_UP, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			int attemptedXMove = player.getTileCoord().getColumn() - 1;
			Tile tile = gameMap.getMapCells().get(new TileCoord(player.getTileCoord().getRow(), attemptedXMove));
			player.moveEntity(Action.MOVE_LEFT, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			int attemptedYMove = player.getTileCoord().getRow() - 1;
			Tile tile = gameMap.getMapCells().get(new TileCoord(attemptedYMove, player.getTileCoord().getColumn()));
			player.moveEntity(Action.MOVE_DOWN, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			int attemptedXMove = player.getTileCoord().getColumn() + 1;
			Tile tile = gameMap.getMapCells().get(new TileCoord(player.getTileCoord().getRow(), attemptedXMove));
			player.moveEntity(Action.MOVE_RIGHT, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
		
		/**
		 * Spawn new enemies
		 */
		if (TimeUtils.nanoTime() - enemySpawnTime > 1000000000 &&
				enemies.size < 4) {
			spawnEnemy();
		}
	}
	
	@Override
	public void dispose () {
		playerImage.dispose();
		enemyImage.dispose();
		batch.dispose();
	}
	
	private void spawnEnemy() {
		Rectangle newEnemy = new Rectangle();
		newEnemy.height = DEFAULT_IMAGE_HEIGHT;
		newEnemy.width = DEFAULT_IMAGE_WIDTH;
		newEnemy.x = MathUtils.random(3, Global.MAP_MAX_CELLS_HORIZONTAL-1) * Global.CELL_WIDTH;
		newEnemy.y = MathUtils.random(2, Global.MAP_MAX_CELLS_VERTICAL-1) * Global.CELL_HEIGHT;
		
		for (int i = 0; i < enemies.size; i ++) {
			if (enemies.get(i).getRectangle().overlaps(newEnemy)) {
				newEnemy.x = MathUtils.random(3, Global.MAP_MAX_CELLS_HORIZONTAL-1) * Global.CELL_WIDTH;
				newEnemy.y = MathUtils.random(2, Global.MAP_MAX_CELLS_VERTICAL-1) * Global.CELL_HEIGHT;
				i = 0;
				System.out.println("Enemy overlapped");
			}
		}

		Entity enemy = new Entity(newEnemy, enemyImage, new TileCoord((int)newEnemy.x, (int)newEnemy.y));
		enemies.add(enemy);
	}
}
