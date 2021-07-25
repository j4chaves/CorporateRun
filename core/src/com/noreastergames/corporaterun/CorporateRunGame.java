package com.noreastergames.corporaterun;

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
	private static final int SCREEN_HEIGHT = 600;
	private static final int SCREEN_WIDTH = 800;
	
	private static final int DEFAULT_IMAGE_HEIGHT = 64;
	private static final int DEFAULT_IMAGE_WIDTH = 64;
	
	private static final int CELL_HEIGHT = 64;
	private static final int CELL_WIDTH = 64;
	private static final int MAP_MAX_CELLS_HORIZONTAL = 12;
	private static final int MAP_MAX_CELLS_VERTICAL = 9;
	
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
		player = new Entity(playerRectangle, playerImage, 0, 0);
		
		gameMap = new GameMap();
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.DARK_GRAY);
		camera.update();
		batch.begin();
		
		for (Tile mapCell : gameMap.getMapCells()) {
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
			int attemptedYMove = player.getyTilePosition() + 1;
			player.moveEntity(Action.MOVE_UP, 0, attemptedYMove);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			int attemptedXMove = player.getxTilePosition() - 1;
			player.moveEntity(Action.MOVE_LEFT, attemptedXMove, 0);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			int attemptedYMove = player.getyTilePosition() - 1;
			player.moveEntity(Action.MOVE_DOWN, 0, attemptedYMove);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			int attemptedXMove = player.getxTilePosition() + 1;
			player.moveEntity(Action.MOVE_RIGHT, attemptedXMove, 0);
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
		newEnemy.x = MathUtils.random(3, MAP_MAX_CELLS_HORIZONTAL-1) * CELL_WIDTH;
		newEnemy.y = MathUtils.random(2, MAP_MAX_CELLS_VERTICAL-1) * CELL_HEIGHT;
		
		for (int i = 0; i < enemies.size; i ++) {
			if (enemies.get(i).getRectangle().overlaps(newEnemy)) {
				newEnemy.x = MathUtils.random(3, MAP_MAX_CELLS_HORIZONTAL-1) * CELL_WIDTH;
				newEnemy.y = MathUtils.random(2, MAP_MAX_CELLS_VERTICAL-1) * CELL_HEIGHT;
				i = 0;
				System.out.println("Enemy overlapped");
			}
		}

		Entity enemy = new Entity(newEnemy, enemyImage, 5, 5);
		enemies.add(enemy);
	}
}
