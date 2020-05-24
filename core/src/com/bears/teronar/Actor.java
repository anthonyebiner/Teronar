package com.bears.teronar;
import com.badlogic.gdx.graphics.Texture;

public class Actor {
    Texture texture;
    Speed movementSpeed;
    Boolean solid;
    int size;
    Boolean visible;
    Position position;

    public Actor(Texture texture) {
        this.texture = texture;
        this.movementSpeed = Speed.STOPPED;
        this.solid = true;
        this.visible = false;
        this.size = 64;
    }
}
