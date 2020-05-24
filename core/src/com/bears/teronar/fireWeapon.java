package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class fireWeapon  extends Actor {
    int damage;
    Teronar game;

    public fireWeapon(Teronar game, Texture texture, Position pos) {
        super(texture);
        this.damage = 6;
        this.game = game;
        this.visible = true;
        this.solid = false;
        this.position = pos;
    }

    public void render() {
        if (visible) {
            game.batch.draw(texture, position.x, position.y);
        }
    }
}
