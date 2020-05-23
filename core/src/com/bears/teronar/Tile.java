package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public abstract class Tile {
    private Boolean visible;
    private Speed movementSpeed;
    private Boolean solid;
    private Texture texture;

    abstract void render(int x, int y);
}
