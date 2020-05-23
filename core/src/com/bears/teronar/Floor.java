package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class Floor extends Tile{

    public Floor(Texture texture) {
        this.texture = texture;
        this.movementSpeed = Speed.MEDIUM;
        this.solid = false;
        this.visible = true;
        this.size = 64;
    }
}
