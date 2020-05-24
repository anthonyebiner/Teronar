package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;
import java.util.Random;

public class Enemy extends Actor{
    final Teronar game;
    float health;
    private Level level;
    float lambda;
    Random rand;
    int movementClock;
    int dx, dy;

    public Enemy(final Teronar game, Level level, Texture texture, Position position) {
        super(texture);
        visible = true;
        this.game = game;
        this.level = level;
        this.position = position;
        this.health = 10;
        this.rand = new Random();
        this.movementClock = 0;
    }

    public void move(int dx, int dy) {
        if (!level.getTile(this.position.x + dx, this.position.y + dy).solid &&
                !level.getTile(this.position.x + 60 + dx, this.position.y + 60 + dy).solid &&
                !level.getTile(this.position.x + 60 + dx, this.position.y + dy).solid &&
                !level.getTile(this.position.x + dx, this.position.y + 60 + dy).solid) {
            position.move(dx, dy);
        }
    }

    public void render() {
        if (this.visible && this.health > 0) {
            game.batch.draw(texture, position.x, position.y);
            if (this.movementClock == -15) {
                this.dx = rand.nextInt(10) - 5;
                this.dy = rand.nextInt(10) - 5;
                this.movementClock = rand.nextInt(10) + 10;
            }
            this.move(this.dx, this.dy);
            this.movementClock -= 1;

        } else {
            this.visible = false;
        }
    }
}
