package com.bears.teronar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Character {
    private Actor character;
    final Teronar game;
    private Texture bulletTexture;
    private ArrayList<Bullet> movingBullets;
    long cooldown;
    private final int MIN_COOLDOWN = 350;
    private int orientation = 0;
    private Level level;
    private int health;
    private int x, y;
    private long lastHitTime;

    public Character(final Teronar game, Level level, Texture mainCharacterTexture, Texture projectile) {
        this.character = new Actor(mainCharacterTexture);
        this.bulletTexture = projectile;
        this.game = game;
        this.level = level;
        movingBullets = new ArrayList<>();
        this.cooldown = System.currentTimeMillis();
        this.health = 100;
        this.lastHitTime = 0;
    }

    public void render(float delta) {
        if (this.health <= 0) {
            return;
        }
        int rotate = 0;
        if (this.orientation == 1) {
            rotate = 90;
        } else if (this.orientation == 2) {
            rotate = 180;
        } else if (this.orientation == 3) {
            rotate = -90;
        }
        game.batch.draw(character.texture, x, y, 32, 32, 64, 64, 1, 1, rotate, 0, 0, 64, 64, false, false);
        x = game.centerX;
        y = game.centerY;
        for (Enemy e : level.actors) {
            if ((x >= e.position.x - 64 - 8 && x <= e.position.x + 8) &&
                    (y >= e.position.y - 64 - 8 && y <= e.position.y + 8) &&
                    System.currentTimeMillis() - this.lastHitTime > 500) {
                this.health -= 25;
                System.out.println("Ouch!");
                this.lastHitTime = System.currentTimeMillis();
            }
        }


        long cooldownDiff = System.currentTimeMillis() - cooldown;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.orientation = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.orientation = 1;
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.orientation = 2;
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.orientation = 3;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && cooldownDiff > MIN_COOLDOWN) {
            this.cooldown = System.currentTimeMillis();
            movingBullets.add(new Bullet(bulletTexture, game, this.level, this.orientation));
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet : movingBullets) {
            bullet.update(delta);
            if (bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }
        movingBullets.removeAll(bulletsToRemove);
        for (Bullet bullet : movingBullets) {
            bullet.render();
        }
    }
}
