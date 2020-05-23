package com.bears.teronar;

public class Level {
    private Tile[][] tiles;
    final Teronar game;

    public Level(final Teronar game, Tile[][] tiles) {
        this.tiles = tiles;
        this.game = game;
    }

    public void render() {
        int minTileX = (game.centerX - game.screenSizeX/2)/64;
        int minTileY = (game.centerY - game.screenSizeY/2)/64;
        int maxTileX = (game.centerX + game.screenSizeX/2)/64;
        int maxTileY = (game.centerY + game.screenSizeY/2)/64;
        for (int i = minTileX; i <= maxTileX; i++) {
            for (int j = minTileY; j <= maxTileY; j++) {
                Tile tile = tiles[j][i];
                if (tile.visible) {
                    game.batch.draw(tile.texture, i*tile.size+5, j*tile.size+5);
                }
            }
        }
    }
}
