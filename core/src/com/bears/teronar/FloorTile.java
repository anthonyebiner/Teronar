package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class FloorTile extends Tile{
    public FloorTile(Texture texture) {
        this.texture = texture;
        this.movementSpeed = Speed.MEDIUM;
        this.solid = false;
        this.visible = true;
        this.size = 64;
    }
}
