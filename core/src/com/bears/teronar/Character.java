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

    public Character(final Teronar game, Level level, Texture mainCharacterTexture, Texture projectile) {
        this.character = new Actor(mainCharacterTexture);
        this.bulletTexture = projectile;
        this.game = game;
        this.level = level;
        movingBullets = new ArrayList<>();
        this.cooldown = System.currentTimeMillis();
    }

    public void render(float delta) {
        game.batch.draw(character.texture, game.centerX, game.centerY);
        long cooldownDiff =  System.currentTimeMillis() - cooldown;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            this.orientation = 0;
        else if (Gdx.input.isKeyPressed(Input.Keys.UP))
            this.orientation = 1;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            this.orientation = 2;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            this.orientation = 3;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && cooldownDiff > MIN_COOLDOWN) {
            System.out.println(this.orientation);
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
