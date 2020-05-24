package com.bears.teronar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class Character{
    private Actor character;
    final Teronar game;
    private Texture bulletTexture;
    private Texture swordTexture;
    private Texture upTexture;
    private ArrayList<Bullet> movingBullets;
    private Animations walkAnimation;
    private Sword sword;
    long cooldown;
    private final int MIN_COOLDOWN = 350;
    private Level level;
    private int health;
    private int x, y;
    private long lastHitTime;
    private int WINDOWMAX_X = 638 + 40;
    private int WINDOWMAX_Y = 478;
    
    public Character(final Teronar game, Level level, Texture mainCharacterTexture, Texture projectile, Texture sword) {
        this.character = new Actor(mainCharacterTexture);
        this.bulletTexture = projectile;
        this.swordTexture = sword;
        this.game = game;
        this.upTexture = game.getTexture("./assets/animations/Queen-Walk-Up.png");
        this.walkAnimation = new Animations(new TextureRegion(this.upTexture), 4, 0.5f);
        this.level = level;
        movingBullets = new ArrayList<>();
        this.cooldown = System.currentTimeMillis();
        this.health = 100;
        level.characterHealth = 100;
        this.lastHitTime = 0;
    }

    public void render(float delta) {
        if (this.health <= 0) {
            return;
        }

        game.batch.draw(character.texture, x, y);
        x = game.centerX;
        y = game.centerY;
        for (Enemy e : level.actors) {
            if ((x >= e.position.x - 64 - 8 && x <= e.position.x + 8) &&
                    (y >= e.position.y - 64 - 8 && y <= e.position.y + 8) &&
                    System.currentTimeMillis() - this.lastHitTime > 500) {
                this.health -= 25;
                level.characterHealth -= 25;
                System.out.println("Ouch!");
                this.lastHitTime = System.currentTimeMillis();
            }
        }

        walkAnimation.update(delta);

        long cooldownDiff =  System.currentTimeMillis() - cooldown;

        float x = Gdx.input.getX() - WINDOWMAX_X / 2;
        float y = WINDOWMAX_Y / 2 - Gdx.input.getY();
        double theta = Math.atan2(y, x);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && cooldownDiff > MIN_COOLDOWN) {
            this.cooldown = System.currentTimeMillis();
            sword = new Sword(swordTexture, game, level, theta);
            sword.render();
        }
        else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && cooldownDiff > MIN_COOLDOWN) {
            this.cooldown = System.currentTimeMillis();
            movingBullets.add(new Bullet(bulletTexture, game, this.level, theta));
        }

        if (sword != null) {
            sword.update();
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
    public TextureRegion getTexture() {
        return walkAnimation.getFrame();
    }

    public void dispose(){
        upTexture.dispose();
    }
}
