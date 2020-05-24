package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Level {
    private Tile[][] tiles;
    final Teronar game;
    ArrayList<Enemy> actors;

    public Level(final Teronar game, Tile[][] tiles) {
        this.tiles = tiles;
        this.game = game;
        this.actors = new ArrayList<>();
    }

    public Level(final Teronar game, Tile[][] tiles, ArrayList<Enemy> actors) {
        this.tiles = tiles;
        this.game = game;
        this.actors = actors;
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(120, 120)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(300, 250)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(800, 60)));
    }

    public int divDown(int x, int y) {
        float res = (float) x / y;
        if (res < 0) {
            return (int) Math.ceil(res);
        } else {
            return (int) Math.floor(res);
        }
    }

    public int div(int x, int y) {
        float res = (float) x / y;
        if (res < 0) {
            return (int) Math.floor(res);
        } else {
            return (int) Math.floor(res);
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

        if (getTile(game.centerX, game.centerY).solid || getTile(game.centerX+64, game.centerY+64).solid
        || getTile(game.centerX+64, game.centerY).solid || getTile(game.centerX, game.centerY+64).solid) {
            game.centerX = oldx;
            game.centerY = oldy;
        }


    }

    public Tile getTile(int x, int y) {
        x = div(x,64);
        y = div(y,64);
        if (x >= 0 && x < tiles[0].length && y >= 0 && y < tiles.length) {
            return tiles[y][x];
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
        for (Enemy actor: actors) {
            actor.render();
        }
    }

    public static Level basicLevel(final Teronar game) {
        FloorTile f1 = new FloorTile(game.getTexture("assets/Dungeon Tile 1.png"));
        FloorTile f2 = new FloorTile(game.getTexture("assets/Dungeon Tile 2.png"));
        FloorTile f3 = new FloorTile(game.getTexture("assets/Dungeon Tile 3.png"));
        FloorTile f11 = new FloorTile(game.getTexture("assets/Dungeon Tile 1-1.png"));
        FloorTile f21 = new FloorTile(game.getTexture("assets/Dungeon Tile 2-1.png"));
        FloorTile f31 = new FloorTile(game.getTexture("assets/Dungeon Tile 3-1.png"));
        WallTile w = new WallTile(game.getTexture("assets/pillar.png"));
        ArrayList<Enemy> actors = new ArrayList<>();

        Tile[][] tiles = {
                {w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
                {w, f11, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, w},
                {w, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, w},
                {w, f2, f2, f2, f2, w, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, w, f2, f2, f2, w},
                {w, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, w, f1, f1, f1, f1, f1, f1, f1, f1, f1, w},
                {w, f3, f3, f3, f3, f31, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, w, f3, f3, f3, f3, f3, f3, f3, f3, f3, w},
                {w, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f21, w},
                {w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w, w},
        };
        return new Level(game, tiles, actors);
    }
}
