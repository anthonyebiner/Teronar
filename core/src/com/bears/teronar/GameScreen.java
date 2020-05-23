package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {
    final Teronar game;
    OrthographicCamera camera;
    Level level;

    public GameScreen(final Teronar game) {
        this.game = game;
        this.level = basicLevel();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenSizeX, game.screenSizeY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        level.render();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private Level basicLevel() {
        FloorTile f1 = new FloorTile(game.getTexture("assets/Dungeon Tile 1.png"));
        FloorTile f2 = new FloorTile(game.getTexture("assets/Dungeon Tile 2.png"));
        FloorTile f3 = new FloorTile(game.getTexture("assets/Dungeon Tile 3.png"));
        Tile[][] tiles = {
                {f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1},
                {f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3},
                {f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2},
                {f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1},
                {f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3},
                {f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2},
                {f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1},
                {f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3},
                {f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2},
                {f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1},
                {f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3},
                {f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2},
        };
        return new Level(game, tiles);
    }
}