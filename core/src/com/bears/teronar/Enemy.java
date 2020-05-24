package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Actor{
    final Teronar game;
    float health;

    public Enemy(final Teronar game, Texture texture) {
        super(texture);
        visible = true;
        this.game = game;
        this.health = 10;
    }

    public Enemy(final Teronar game, Texture texture, Position position) {
        super(texture);
        visible = true;
        this.game = game;
        this.position = position;
        this.health = 10;
    }

    public void move(int dx, int dy) {
        position.move(dx, dy);
    }

    public void render() {
        if (this.visible && this.health > 0) {
            game.batch.draw(texture, position.x, position.y);
        } else {
            this.visible = false;
        }
    }
}
