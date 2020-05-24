package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;

public class Wizard extends Actor {
    private Level level;
    private Teronar game;
    public int x, y;
    public Wizard (Texture texture, final Teronar game, Level level){
        super(texture);
        this.game = game;
        this.level = level;
        x = 100;
        y = 300;
    }
    public void render(){
        game.batch.draw(texture, x, y);
        if ((x-game.centerX)*(x-game.centerX) + (y-game.centerY)*(y-game.centerY) < 2500){
            game.font.draw(game.batch, "Press X to Interact", game.centerX, game.centerY-100);
            
        }
    }
}
