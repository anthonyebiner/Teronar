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
    private double lifetime;
    private double age;
    private double theta;

    public static final int SPEED = 500;
    public boolean remove = false;
    private int damage = 3;

    public Bullet(Texture texture, final Teronar game, Level level, double theta) {
        this.texture = texture;
        this.movementSpeed = Speed.MEDIUM;
        this.solid = true;
        this.visible = false;
        this.size = 64;
        this.game = game;
        this.cooldown = 0;
        this.x = game.centerX;
        this.y = game.centerY;
        this.theta = theta;
        this.level = level;
        this.lifetime = 0.5;
        this.age = 0.0;
    }

    public void setDamage(int d) {
        damage = d;
    }

    public void update (float deltaTime) {
        this.age += deltaTime;
        if (checkScreenBoundary(deltaTime) || (this.age >= this.lifetime)) {
            // If the bullet goes offscreen or has lived its lifetime...
            remove = true; // Remove it.
            return;
        } else if (level.getTile(Math.round(this.x+this.size/2), Math.round(this.y+this.size/2)).solid){
            // Wall collision
            remove = true;
            return;
        } else {
            for (Enemy e : level.actors) {
                if ((x >= e.position.x - 64 - 8 && x <= e.position.x + 8) &&
                        (y >= e.position.y - 64 - 8 && y <= e.position.y + 8)) {
                    remove = true;
                    e.health -= damage;
                }
            }
            if ((x >= level.boss.x - 64 - 8 && x <= level.boss.x + 8) &&
                    (y >= level.boss.y - 64 - 8 && y <= level.boss.y + 8)) {
                remove = true;
                level.boss.health -= damage;
            }
        }
    }

    private boolean checkScreenBoundary(float deltaTime) {
        x += SPEED * deltaTime * Math.cos(theta);
        y += SPEED * deltaTime * Math.sin(theta);
        if ((x > game.centerX + game.screenSizeX/2) ||
                (y < game.centerY - game.screenSizeY/2) ||
                (x < game.centerX - game.screenSizeX/2) ||
                (y > game.centerY + game.screenSizeY/2)) {
                remove = true;
        }
        return false;
    }

    public void render() {
         game.batch.draw(this.texture, x, y, 32, 32, 64, 64, 1, 1, (float) (theta * 180 / Math.PI), 0, 0, 64, 64, false, false);
    }
}
