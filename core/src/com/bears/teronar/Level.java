package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Level {
    private Tile[][] tiles;
    final Teronar game;

    public Level(final Teronar game, Tile[][] tiles) {
        this.tiles = tiles;
        this.game = game;
    }

    public int divDown(int x, int y) {
        float res = (float) x / y;
        if (res < 0) {
            return (int) Math.ceil(res);
        } else {
            return (int) Math.floor(res);
        }
    }

    public int divUp(int x, int y) {
        float res = (float) x / y;
        if (res < 0) {
            return (int) Math.floor(res);
        } else {
            return (int) Math.ceil(res);
        }
    }

    public void handleMovement() {
        int oldx = game.centerX;
        int oldy = game.centerY;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            game.centerX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            game.centerX += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            game.centerY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            game.centerY -= 200 * Gdx.graphics.getDeltaTime();

        if (getTile(game.centerX, game.centerY).solid) {
            game.centerX = oldx;
            game.centerY = oldy;
        }
    }

    public Tile getTile(int x, int y) {
        x = divUp(x, 64);
        y = divUp(y, 64);
        if (x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length) {
            return tiles[divDown(y,64)][divDown(x, 64)];
        } else {
            return game.blankTile;
        }
    }

    public void render() {
        int minX = (game.centerX - game.screenSizeX/2);
        int minY = (game.centerY - game.screenSizeY/2);
        int maxX = (game.centerX + game.screenSizeX/2);
        int maxY = (game.centerY + game.screenSizeY/2);
        for (int x = divDown(minX,64); x <= divDown(maxX, 64); x++) {
            for (int y = divDown(minY, 64); y <= divDown(maxY, 64); y++) {
                if (x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length) {
                    Tile tile = tiles[y][x];
                    if (tile.visible) {
                        game.batch.draw(tile.texture, x*tile.size, y*tile.size);
                    }
                }
            }
        }
        game.batch.draw(game.getTexture("assets/King-Up.png"), game.centerX, game.centerY);
    }
}
