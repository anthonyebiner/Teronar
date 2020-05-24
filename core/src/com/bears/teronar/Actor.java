package com.bears.teronar;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Actor {
    TextureRegion texture;
    Speed movementSpeed;
    Boolean solid;
    int size;
    Boolean visible;
    Position position;

    public Actor(Texture texture) {
        this.texture = new TextureRegion(texture);
        this.movementSpeed = Speed.STOPPED;
        this.solid = true;
        this.visible = false;
        this.size = 64;
    }
}
