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
	private Rectangle playerRectangle;
	
	private Texture enemyImage;
	private Array<Rectangle> enemies;;
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
		playerRectangle = new Rectangle(50, 50, 64, 64);
		enemies = new Array<>();
	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.DARK_GRAY);
		camera.update();
		batch.begin();
		batch.draw(playerImage, playerRectangle.x, playerRectangle.y);
		
		for(Rectangle enemy : enemies) {
			batch.draw(enemyImage, enemy.x, enemy.y);
			
			if (enemy.overlaps(playerRectangle)) {
				enemies.removeValue(enemy, false);
			}
		}
		batch.end();
		
		
		/**
		 *  Keyboard Controls
		 */
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			if (playerRectangle.y + 64 > SCREEN_HEIGHT) {
				playerRectangle.y = SCREEN_HEIGHT - 64;
			} else {
				playerRectangle.y += CELL_HEIGHT;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			if (playerRectangle.x < 0) {
				playerRectangle.x = 0;
			} else {
				playerRectangle.x -= CELL_WIDTH;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			if (playerRectangle.y < 0) {
				playerRectangle.y = 0;
			} else {
				playerRectangle.y -= CELL_HEIGHT;
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			if (playerRectangle.x + 64 > SCREEN_WIDTH) {
				playerRectangle.x = SCREEN_WIDTH - 64;
			} else {
				playerRectangle.x += CELL_WIDTH;
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
		newEnemy.x = MathUtils.random(200, 500);
		newEnemy.y = MathUtils.random(200, 400);
		
		for (int i = 0; i < enemies.size; i ++) {
			if (enemies.get(i).overlaps(newEnemy)) {
				newEnemy.x = MathUtils.random(200, 500);
				newEnemy.y = MathUtils.random(200, 400);
				i = 0;
				System.out.println("Enemy overlapped");
			}
		}

		enemies.add(newEnemy);
	}
}
