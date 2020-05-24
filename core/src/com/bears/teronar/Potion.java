package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class Potion extends Actor {
    int healing;
    Teronar game;

    public Potion(Teronar game, Texture texture, Position pos) {
        super(texture);
        this.healing = 25;
        this.game = game;
        this.visible = true;
        this.solid = false;
        this.position = pos;
    }

    public void render() {
        if (visible)
            game.batch.draw(texture, position.x, position.y);
    }
}
