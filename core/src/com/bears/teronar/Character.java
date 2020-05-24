package com.bears.teronar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Character{
    private Actor character;
    final Teronar game;
    public Texture bulletTexture;
    private TextureRegion bladeTexture;
    private Texture upTexture;
    private Texture downTexture;
    private Texture leftTexture;
    private Texture rightTexture;
    private ArrayList<Bullet> movingBullets;
    private Animations walkUpAnimation;
    private Animations walkDownAnimation;
    private Animations walkLeftAnimation;
    private Animations walkRightAnimation;
    private String monarch_gender;
    private Blade sword;
    long cooldown;
    private final int MIN_COOLDOWN = 350;
    private Level level;
    public int health;
    private int x, y;
    private long lastHitTime;
    private int WINDOWMAX_X = 638 + 40;
    private int WINDOWMAX_Y = 478;
    public int maxHealth;
    public int bulletDamage = 3;

    
    public Character(final Teronar game, Level level, String gender, Texture projectile, String weapon) {
        this.upTexture = game.getTexture("./assets/" + gender + "-Up.png");
        this.downTexture = game.getTexture("./assets/" + gender + "-Down.png");
        this.rightTexture = game.getTexture("./assets/" + gender + "-Right.png");
        this.leftTexture = game.getTexture("./assets/" + gender + "-Left.png");
        this.character = new Actor(this.upTexture);
        this.bulletTexture = projectile;
        this.bladeTexture = new TextureRegion(new Texture("./assets/animations/" + weapon + "-Slash.png"));
        this.game = game;

        this.walkUpAnimation = new Animations(new TextureRegion(game.getTexture("./assets/animations/" + gender + "-Walk-Up.png")), 4, 0.5f);
        this.walkDownAnimation = new Animations(new TextureRegion(game.getTexture("./assets/animations/" + gender + "-Walk-Down.png")), 4, 0.5f);
        this.walkLeftAnimation = new Animations(new TextureRegion(game.getTexture("./assets/animations/" + gender + "-Walk-Left.png")), 4, 0.5f);
        this.walkRightAnimation = new Animations(new TextureRegion(game.getTexture("./assets/animations/" + gender + "-Walk-Right.png")), 4, 0.5f);
        this.level = level;
        movingBullets = new ArrayList<>();
        this.cooldown = System.currentTimeMillis();
        this.health = 100;
        this.maxHealth = 100;
        this.lastHitTime = 0;
    }

    public void render(float delta) {
        if (this.health <= 0) {
            return;
        }

        game.batch.draw(character.texture, x, y); // Display the character at the initial position when rendered.
        x = game.centerX;
        y = game.centerY;
        for (Enemy e : level.actors) {
            if (collision(x,y,e)) {
                this.health -= 20;
                this.lastHitTime = System.currentTimeMillis();
            }
        }
        if (collision(x,y,level.boss)) {
            this.health -= 30;
            this.lastHitTime = System.currentTimeMillis();
        }


        long cooldownDiff =  System.currentTimeMillis() - cooldown;

        float x = Gdx.input.getX() - WINDOWMAX_X / 2;
        float y = WINDOWMAX_Y / 2 - Gdx.input.getY();
        double theta = Math.atan2(y, x);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && cooldownDiff > MIN_COOLDOWN) {
            this.cooldown = System.currentTimeMillis();
            sword = new Blade(bladeTexture, game, level, theta);
            sword.render();
        }
        else if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && cooldownDiff > MIN_COOLDOWN) {
            this.cooldown = System.currentTimeMillis();
            Bullet b = new Bullet(bulletTexture, game, this.level, theta);
            b.setDamage(bulletDamage);
            movingBullets.add(b);
        }

        if (sword != null) {
            sword.update(delta);
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
        return walkUpAnimation.getFrame();
    }
    public void look(String direction){
        if (direction == "Up"){
            character.texture = new TextureRegion(upTexture);
        } else if (direction == "Down") {
            character.texture = new TextureRegion(downTexture);
        } else if (direction == "Left") {
            character.texture = new TextureRegion(leftTexture);
        } else if (direction == "Right") {
            character.texture = new TextureRegion(rightTexture);
        }
    }
    public void move(String direction, float delta){
        if (direction == "Up"){
            walkUpAnimation.update(delta);
            character.texture = walkUpAnimation.getFrame();
        } else if (direction == "Down") {
            walkDownAnimation.update(delta);
            character.texture = walkDownAnimation.getFrame();
        } else if (direction == "Left") {
            walkLeftAnimation.update(delta);
            character.texture = walkLeftAnimation.getFrame();
        } else if (direction == "Right") {
            walkRightAnimation.update(delta);
            character.texture = walkRightAnimation.getFrame();
        }
    }
    public Boolean collision(float x, float y, Boss boss){
        return ((x >= level.boss.x - 64 - 8 && x <= level.boss.x + 8) &&
                (y >= level.boss.y - 64 - 8 && y <= level.boss.y + 8) && System.currentTimeMillis() - this.lastHitTime > 500);
    }
    public Boolean collision(float x, float y, Enemy enemy){
        return ((x >= enemy.position.x - 64 - 8 && x <= enemy.position.x + 8) &&
                (y >= enemy.position.y - 64 - 8 && y <= enemy.position.y + 8) && System.currentTimeMillis() - this.lastHitTime > 500);
    }
}
