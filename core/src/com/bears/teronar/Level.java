package com.bears.teronar;

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

    public void render() {
        int minX = (game.centerX - game.screenSizeX/2);
        int minY = (game.centerY - game.screenSizeY/2);
        int maxX = (game.centerX + game.screenSizeX/2);
        int maxY = (game.centerY + game.screenSizeY/2);
        for (int x = divUp(minX,64); x <= divDown(maxX, 64); x++) {
            for (int y = divUp(minY, 64); y <= divDown(maxY, 64); y++) {
                if (x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length) {
                    Tile tile = tiles[x][y];
                    if (tile.visible) {
                        game.batch.draw(tile.texture, x*tile.size+minX, y*tile.size+minY);
                    }
                }
            }
        }
    }
}
