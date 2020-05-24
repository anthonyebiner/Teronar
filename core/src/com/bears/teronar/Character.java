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
    private final int MIN_COOLDOWN = 250;

    public Character(final Teronar game, Texture mainCharacterTexture, Texture projectile) {
        this.character = new Actor(mainCharacterTexture);
        this.bulletTexture = projectile;
        this.game = game;
        movingBullets = new ArrayList<>();
        this.cooldown = System.currentTimeMillis();
    }

    public void render(float delta) {
        game.batch.draw(character.texture, game.centerX, game.centerY);
        long cooldownDiff =  System.currentTimeMillis() - cooldown;
        System.out.println(cooldownDiff);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && cooldownDiff > MIN_COOLDOWN) {
            this.cooldown = System.currentTimeMillis();
            movingBullets.add(new Bullet(bulletTexture, game));
        }

        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for (Bullet bullet : movingBullets) {
            bullet.update(delta);
            if (bullet.remove)
                bulletsToRemove.add(bullet);
        }
        movingBullets.removeAll(bulletsToRemove);
        for (Bullet bullet : movingBullets) {
            bullet.render();
        }
    }
}
