package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

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
            if (Gdx.input.isKeyPressed(Input.Keys.X)){
                game.font.getData().setScale(1.5f);
                Label dialogue = new Label("Your Majesty! Quickly, with your royal blade and the magic I've given you, save your kingdom!", new LabelStyle(game.font, Color.BLUE));
                dialogue.setBounds(game.centerX-450,game.centerY,20,150);
                dialogue.draw(game.batch, 1);
                game.font.getData().setScale(0.666f);
                }
        }
    }
}
