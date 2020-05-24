package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class WallTile extends Tile{
    public WallTile(Texture texture) {
        this.texture = texture;
        this.movementSpeed = Speed.STOPPED;
        this.solid = true;
        this.visible = true;
        this.size = 64;
    }
}
