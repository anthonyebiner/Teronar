package com.bears.teronar;
import com.badlogic.gdx.graphics.Texture;

public class Character {
    private Actor character;
    final Teronar game;

    public Character(final Teronar game, Texture mainCharacterTexture) {
        this.character = new Actor(mainCharacterTexture);
        this.game = game;
    }

    public void render() {
        game.batch.draw(character.texture, game.centerX, game.centerY);
    }
}
