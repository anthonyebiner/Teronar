package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;



public class Bullet {
    Texture texture;
    Speed movementSpeed;
    Boolean solid;
    int size;
    Boolean visible;
    final Teronar game;
    private int cooldown;
    float x, y;

    public static final int SPEED = 500;
    public boolean remove = false;

    public Bullet(Texture texture, final Teronar game) {
        this.texture = texture;
        this.movementSpeed = Speed.MEDIUM;
        this.solid = true;
        this.visible = false;
        this.size = 64;
        this.game = game;
        this.cooldown = 0;
        this.x = game.centerX;
        this.y = game.centerY;
    }

    public void update (float deltaTime) {
        x += SPEED * deltaTime;
        if (x > game.screenSizeX)
            remove = true;
    }

    public void render() {
        game.batch.draw(this.texture, x, y);

    }
}
