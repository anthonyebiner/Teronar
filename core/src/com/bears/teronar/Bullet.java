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
    private Level level;
    private int cooldown;
    float x, y;
    private int orientation;

    public static final int SPEED = 500;
    public boolean remove = false;
    private static final int damage = 5;

    public Bullet(Texture texture, final Teronar game, Level level, int orientation) {
        this.texture = texture;
        this.movementSpeed = Speed.MEDIUM;
        this.solid = true;
        this.visible = false;
        this.size = 64;
        this.game = game;
        this.cooldown = 0;
        this.x = game.centerX;
        this.y = game.centerY;
        this.orientation = orientation;
        this.level = level;
    }

    public void update (float deltaTime) {
        if (checkScreenBoundary(deltaTime)) {
            // Screen condition
            return;
        } else if (level.getTile(Math.round(this.x), Math.round(this.y)).solid){
            // Wall collision
            remove = true;
            return;
        } else {
            for (Enemy e : level.actors) {
                if ((x >= e.position.x - 64 - 8 && x <= e.position.x + 8) &&
                        (y >= e.position.y - 64 - 8&& y <= e.position.y + 8)) {
                    remove = true;
                    e.health -= damage;
                }
            }
        }
    }

    private boolean checkScreenBoundary(float deltaTime) {
        if (this.orientation == 0) {
            x += SPEED * deltaTime;
            if (x > game.centerX + game.screenSizeX/2)
                remove = true;
        } else if (this.orientation == 1) {
            y += SPEED * deltaTime;
            if (y < game.centerY - game.screenSizeY/2)
                remove = true;
        } else if (this.orientation == 2) {
            x -= SPEED * deltaTime;
            if (x < game.centerX - game.screenSizeX/2)
                remove = true;
        } else if (this.orientation == 3) {
            y -= SPEED * deltaTime;
            if (y > game.centerY + game.screenSizeY/2)
                remove = true;
        }
        return false;
    }

    public void render() {
        int rotate = 0;
        if (this.orientation == 1) {
            rotate = 90;
        } else if (this.orientation == 3) {
            rotate = -90;
        }
        game.batch.draw(this.texture, x, y, 32, 32, 64, 64, 1, 1, rotate, 0, 0, 64, 64, false, false);

    }
}
