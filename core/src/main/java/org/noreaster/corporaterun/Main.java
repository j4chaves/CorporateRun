package org.noreaster.corporaterun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import squidpony.squidgrid.gui.gdx.FilterBatch;
import squidpony.squidgrid.gui.gdx.FloatFilter;
import squidpony.squidgrid.gui.gdx.FloatFilters;
import squidpony.squidgrid.gui.gdx.SColor;
import squidpony.squidgrid.gui.gdx.SparseLayers;
import squidpony.squidgrid.gui.gdx.SquidInput;
import squidpony.squidgrid.gui.gdx.TextCellFactory;
import squidpony.squidmath.Coord;

public class Main extends ApplicationAdapter {
	
	private static final int cellWidth = 10;
	private static final int cellHeight = 20;

	private FilterBatch filterBatch;

	private SquidInput input;

	private SparseLayers display;

	private Color backgroundColor;

	private Stage stage;

	private Coord playerCoord;
	private TextCellFactory.Glyph playerGlyph;
	
	private Vector2 screenPosition;

	@Override
	public void create() {
		FloatFilter floatFilter = new FloatFilters.YCwCmFilter(0.875f, 0.6f, 0.6f);
		filterBatch = new FilterBatch(floatFilter);

		StretchViewport mainViewport = new StretchViewport(800, 600);
		stage = new Stage(mainViewport, filterBatch);

		backgroundColor = SColor.DARK_SLATE_GRAY;

		playerCoord = Coord.get(400, 350);
		playerCoord.setX(400);
		playerCoord.setY(350);

		display = new SparseLayers(1200, 800);
		display.setDefaultBackground(backgroundColor);
		playerGlyph = display.glyph('@', SColor.SAPPANWOOD, playerCoord.x, playerCoord.y);

		input = new SquidInput(new SquidInput.KeyHandler() {

			@Override
			public void handle(char key, boolean alt, boolean ctrl, boolean shift) {
				switch (key) {
					case 'W':
					case 'w': {
						System.out.println("W");
						break;
					}
					case 's':
					case 'S': {
						System.out.println("S");
						break;
					}

					case 'a':
					case 'A': {
						System.out.println("a");
						break;
					}

					case 'd':
					case 'D': {
						System.out.println("d");
						break;
					}
					
					case 'q': {
						System.exit(0);
					}

				}
			}
		});
		
		//Gdx.input.setInputProcessor(new InputMultiplexer(stage, input));
		Gdx.input.setInputProcessor(input);
		
		
		stage.addActor(display);
		stage.addActor(playerGlyph);
		
		screenPosition = new Vector2(cellWidth, cellHeight);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(backgroundColor.r / 255.0f, backgroundColor.g / 255.0f, backgroundColor.b / 255.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.getCamera().position.x = playerGlyph.getX();
		stage.getCamera().position.y = playerGlyph.getY();

		stage.getViewport().apply(false);

		filterBatch.setProjectionMatrix(stage.getCamera().combined);
		stage.screenToStageCoordinates(new Vector2().set(400, 600));
		filterBatch.begin();
		stage.getRoot().draw(filterBatch, 1);
		display.font.draw(filterBatch, Gdx.graphics.getFramesPerSecond() + " FPS", 400, 600);
		filterBatch.end();
		Gdx.graphics.setTitle("Corporate Run - FPS: " + Gdx.graphics.getFramesPerSecond());

	}
}
