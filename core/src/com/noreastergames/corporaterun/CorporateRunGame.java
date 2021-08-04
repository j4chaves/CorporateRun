package com.noreastergames.corporaterun;

import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class CorporateRunGame extends ApplicationAdapter {
	private static final int SCREEN_HEIGHT = 1024;
	private static final int SCREEN_WIDTH = 1280;
	
	private Texture playerImage;
	private Entity player;
	
	private Texture enemyImage;
	private Array<Entity> enemies;
	
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
		
		Rectangle playerRectangle = new Rectangle(3*32, 3*32, Global.CELL_WIDTH, Global.CELL_HEIGHT);
		player = new Entity(playerRectangle, playerImage, new GridPoint2(3, 3));
		
		gameMap = new ProcGen().generateDungeon(Global.MAP_MAX_CELLS_HORIZONTAL, Global.MAP_MAX_CELLS_VERTICAL);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.DARK_GRAY);
		camera.update();
		batch.begin();
		
		for (Map.Entry<GridPoint2, Tile> entry : gameMap.getMapCells().entrySet()) {
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
			int attemptedYMove = player.getGridPoint2().y + 1;
			Tile tile = gameMap.getMapCells().get(new GridPoint2(player.getGridPoint2().x, attemptedYMove));
			player.moveEntity(Action.MOVE_UP, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			int attemptedXMove = player.getGridPoint2().x - 1;
			Tile tile = gameMap.getMapCells().get(new GridPoint2(attemptedXMove, player.getGridPoint2().y));
			player.moveEntity(Action.MOVE_LEFT, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			int attemptedYMove = player.getGridPoint2().y - 1;
			Tile tile = gameMap.getMapCells().get(new GridPoint2(player.getGridPoint2().x, attemptedYMove));
			player.moveEntity(Action.MOVE_DOWN, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			int attemptedXMove = player.getGridPoint2().x + 1;
			Tile tile = gameMap.getMapCells().get(new GridPoint2(attemptedXMove, player.getGridPoint2().y));
			player.moveEntity(Action.MOVE_RIGHT, tile);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			Gdx.app.exit();
		}
		
		
		/**
		 * Spawn new enemies
		 */
		if (enemies.size < 4) {
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
		newEnemy.height = Global.CELL_HEIGHT;
		newEnemy.width = Global.CELL_WIDTH;
		
		Array<Tile> walkableCells = gameMap.getWalkableCells();
		Tile walkableTile = walkableCells.random();
		newEnemy.x = walkableTile.getGridPoint2().x * 32;
		newEnemy.y = walkableTile.getGridPoint2().y * 32;

		Entity enemy = new Entity(newEnemy, enemyImage, new GridPoint2(walkableTile.getGridPoint2().x, walkableTile.getGridPoint2().y));
		enemies.add(enemy);
	}
}
