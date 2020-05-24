package com.bears.teronar;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {

        private static final int COLUMNS = 2, ROWS = 2;

        Animation<TextureRegion> walkAnimation;
        Texture walkSpriteSheet;
        SpriteBatch spriteBatch;

        float delta;

        @Override
        public void create() {

        }


        public void create(String filepath) {
            walkSpriteSheet = new Texture(Gdx.files.internal(filepath));

            TextureRegion[][] cycle = TextureRegion.split(walkSpriteSheet,
                    walkSpriteSheet.getWidth() / COLUMNS,
                    walkSpriteSheet.getHeight() / ROWS);

            TextureRegion[] walkingFrames = new TextureRegion[COLUMNS * ROWS];
            int index = 0;
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLUMNS; j++) {
                    walkingFrames[index++] = cycle[i][j];
                }
            }

            walkAnimation = new Animation<TextureRegion>(0.25f, walkingFrames);

            spriteBatch = new SpriteBatch();
            delta = 0f;
        }

    @Override
    public void resize(int width, int height) {

    }

    public void render() {
            Gdx.gl.glClearColor(0, 0, 0 , 0);
            delta += Gdx.graphics.getDeltaTime();

            TextureRegion currentFrame = walkAnimation.getKeyFrame(delta, true);
            spriteBatch.begin();
            spriteBatch.draw(currentFrame, 64, 64);
            spriteBatch.end();
        }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void dispose() {
            spriteBatch.dispose();
            walkSpriteSheet.dispose();
        }
}
