package com.noreastergames.corporaterun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	
	private Texture playerImage;
	private Entity player;
	
	private Texture enemyImage;
	private Array<Entity> enemies;
	private long enemySpawnTime;
	
	private Sound testSound;
	private Music testMusic;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		// Load images for player + enemy, 64x64 pixels
		playerImage = new Texture(Gdx.files.internal("player.png"));
		enemyImage = new Texture(Gdx.files.internal("enemy.png"));
		
		// Load sound effects and music - TODO implement later
		//testSound = Gdx.audio.newSound(Gdx.files.internal("testSound.wav"));
		//testMusic = Gdx.audio.newMusic((Gdx.files.internal("testMusic.mp3"));
		
		// Start background music - TODO implement later
		//testMusic.setLooping(true);
		//testMusic.play();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		batch = new SpriteBatch();
		
		// Instantiate Rectangles		
		enemies = new Array<>();
		
		Rectangle playerRectangle = new Rectangle(50, 50, 64, 64);
		player = new Entity(playerRectangle, playerImage);
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.DARK_GRAY);
		camera.update();
		batch.begin();
		batch.draw(player.getTexture(), player.getRectangle().x, player.getRectangle().y);
		
		for(Entity enemy : enemies) {
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
			if (player.getRectangle().y + 64 > SCREEN_HEIGHT) {
				player.getRectangle().y = SCREEN_HEIGHT - 64;
			} else {
				player.getRectangle().y += CELL_HEIGHT;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			if (player.getRectangle().x < 0) {
				player.getRectangle().x = 0;
			} else {
				player.getRectangle().x -= CELL_WIDTH;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			if (player.getRectangle().y < 0) {
				player.getRectangle().y = 0;
			} else {
				player.getRectangle().y -= CELL_HEIGHT;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			if (player.getRectangle().x + 64 > SCREEN_WIDTH) {
				player.getRectangle().x = SCREEN_WIDTH - 64;
			} else {
				player.getRectangle().x += CELL_WIDTH;
			}
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
		testSound.dispose();
		testMusic.dispose();
		batch.dispose();
	}
	
	private void spawnEnemy() {
		Rectangle newEnemy = new Rectangle();
		newEnemy.height = DEFAULT_IMAGE_HEIGHT;
		newEnemy.width = DEFAULT_IMAGE_WIDTH;
		newEnemy.x = MathUtils.random(150, 500);
		newEnemy.y = MathUtils.random(150, 400);
		
		for (int i = 0; i < enemies.size; i ++) {
			if (enemies.get(i).getRectangle().overlaps(newEnemy)) {
				newEnemy.x = MathUtils.random(200, 500);
				newEnemy.y = MathUtils.random(200, 400);
				i = 0;
				System.out.println("Enemy overlapped");
			}
		}

		Entity enemy = new Entity(newEnemy, enemyImage);
		enemies.add(enemy);
	}
}
