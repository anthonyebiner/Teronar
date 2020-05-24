package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Blade {
    TextureRegion texture;
    Animations animation;
    Speed movementSpeed;
    Boolean solid;
    int size;
    Boolean visible;
    final Teronar game;
    private Level level;
    private long cooldown;
    float x, y;
    private double theta;

    public static final int SPEED = 500;
    public boolean remove = false;
    private static final int damage = 5;

    public Blade(TextureRegion texture, final Teronar game, Level level, double theta) {
        this.animation = new Animations(texture, 4, 0.25f);
        this.texture = this.animation.getFrame();
        this.movementSpeed = Speed.MEDIUM;
        this.solid = true;
        this.visible = false;
        this.size = 64;
        this.game = game;
        this.cooldown = System.currentTimeMillis();
        this.x = game.centerX;
        this.y = game.centerY;
        this.theta = theta;
        this.level = level;
    }

    public void update (float delta) {
        if (System.currentTimeMillis() - this.cooldown > 200) {
            return;
        }
        this.animation.update(delta);
        this.texture = this.animation.getFrame();
        this.render();
        for (Enemy e : level.actors) {
            if (collision(x,y,e)) {
                System.out.println("Gottem");
                e.health -= damage;
                e.lastHit = System.currentTimeMillis();
            }
        }

    }

    public void render() {

         game.batch.draw(this.texture, x + 32, y + 32, 0, 0, 64, 64, 1, 1, (float) (theta * 180 / Math.PI - 45));
    }
    public Boolean collision(float x, float y, Enemy e){
        return ((x >= e.position.x - 64 - 8 && x <= e.position.x + 48 + 8) &&
                (y >= e.position.y - 64 - 8 && y <= e.position.y + 48 + 8) &&
                System.currentTimeMillis() - e.lastHit > 200);
    }
}
