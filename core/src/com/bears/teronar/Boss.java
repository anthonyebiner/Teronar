package com.bears.teronar;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class Boss {
    private Actor character;
    final Teronar game;
    private Texture bulletTexture;
    private Texture swordTexture;
    private ArrayList<BossBullet> movingBullets;
    private Blade sword;
    long cooldown;
    private final int MIN_COOLDOWN = 350;
    private Level level;
    public int health;
    public int x, y;
    private long lastHitTime;
    private Random rand;

    public Boss(final Teronar game, Level level, Texture bossTexture, Texture projectile) {
        this.character = new Actor(bossTexture);
        this.bulletTexture = projectile;
        this.game = game;
        this.level = level;
        movingBullets = new ArrayList<>();
        this.cooldown = 0;
        this.health = 100;
        this.lastHitTime = 0;
        this.rand = new Random();
        x = 575;
        y = 3550;
    }

    public void render(float delta) {
        if (this.health <= 0 ||
                !(Math.abs(game.centerX - x) < game.screenSizeX / 2 &&
                Math.abs(game.centerY - y) < game.screenSizeY / 2 )) {
            return;
        }
        game.batch.draw(character.texture, x, y);
        if (this.cooldown <= 0) {
            this.cooldown = rand.nextInt(60) + 200;
            for (int i = 1; i <= 6; i++) {
                double theta = (360 / 6 * i) * Math.PI / 180;
                movingBullets.add(new BossBullet(bulletTexture, game, this.level, theta, x, y));
            }
        } else if (this.cooldown == 245) {
            level.spawnEnemy(x + rand.nextInt(50) - 25, y + rand.nextInt(50) - 25);
            this.cooldown -= 1;
        } else if (this.cooldown == 180) {
            for (int i = 1; i <= 8; i++) {
                double theta = (360 / 8 * i + 45/2) * Math.PI / 180;
                movingBullets.add(new BossBullet(bulletTexture, game, this.level, theta, x, y));
            }
            this.cooldown -= 1;
        } else if (this.cooldown == 150){
            for (int i = 1; i <= 6; i++) {
                double theta = (360 / 6 * i) * Math.PI / 180;
                movingBullets.add(new BossBullet(bulletTexture, game, this.level, theta, x, y));
            }
            this.cooldown -= 1;
        } else {
            this.cooldown -= 1;
        }


        ArrayList<BossBullet> bulletsToRemove = new ArrayList<BossBullet>();
        for (BossBullet bullet : movingBullets) {
            bullet.update(delta);
            if (bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }
        movingBullets.removeAll(bulletsToRemove);
        for (BossBullet bullet : movingBullets) {
            bullet.render();
        }
    }
}
