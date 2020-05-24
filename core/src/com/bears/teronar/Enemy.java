package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Actor{
    final Teronar game;

    public Enemy(final Teronar game, Texture texture) {
        super(texture);
        visible = true;
        this.game = game;
    }

    public Enemy(final Teronar game, Texture texture, Position position) {
        super(texture);
        visible = true;
        this.game = game;
        this.position = position;
    }

    public void move(int dx, int dy) {
        position.move(dx, dy);
    }

    public void render() {
        game.batch.draw(texture, position.x, position.y);
    }
}
