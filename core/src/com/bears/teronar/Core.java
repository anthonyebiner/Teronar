package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class Core {
    private HashMap<String, Texture> loaded;

    public Core() {
        loaded = new HashMap<>();
    }

    public Texture getTexture(String path) {
        if (loaded.containsKey(path)) {
            return loaded.get(path);
        } else {
            Texture texture = new Texture(path);
            loaded.put(path, texture);
            return texture;
        }
    }
}
