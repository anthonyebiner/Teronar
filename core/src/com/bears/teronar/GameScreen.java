package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class GameScreen implements Screen {
    final Teronar game;
    OrthographicCamera camera;
    Level level;
    Character character;
    Boss boss;

    public GameScreen(final Teronar game) {
        this.game = game;
        this.level = Level.betterLevel(game);

        this.character = new Character(game, level, "Queen", game.getTexture("assets/bolt_standard.png"), "Dagger");


        this.character = new Character(game, level, "Queen", game.getTexture("assets/bolt_standard.png"), "Dagger");
        this.boss = new Boss(game, level,game.getTexture("assets/King-Up.png"),game.getTexture("assets/bolt_necromancer.png"));
        level.boss = this.boss;
        level.character = character;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenSizeX, game.screenSizeY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(game.centerX, game.centerY, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        level.render();
        character.render(delta);
        boss.render(delta);
        game.batch.end();

        level.handleMovement();
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
}