package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class BlankTile extends Tile{
    public BlankTile() {
        this.texture = null;
        this.movementSpeed = Speed.STOPPED;
        this.solid = true;
        this.visible = false;
        this.size = 64;
    }
}
