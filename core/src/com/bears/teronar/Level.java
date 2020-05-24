package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Level{
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
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(300, 800)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(850, 1050)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(900, 1050)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(110, 2300)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(150, 2300)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(125, 2270)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(500, 1500)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(500, 3500)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(550, 3500)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(450, 3500)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(400, 3500)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(500, 3600)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(550, 3600)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(450, 3600)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(400, 3600)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(500, 3700)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(550, 3700)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(450, 3700)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(400, 3700)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(500, 3750)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(550, 3750)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(450, 3750)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(400, 3750)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(500, 3650)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(550, 3650)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(450, 3650)));
        actors.add(new Enemy(game, this, game.getTexture("assets/pot_health.png"), new Position(400, 3650)));
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
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            game.centerX -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            game.centerX += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            game.centerY += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            game.centerY -= 200 * Gdx.graphics.getDeltaTime();


        if (getTile(game.centerX, game.centerY).solid || getTile(game.centerX+60, game.centerY+60).solid
        || getTile(game.centerX+60, game.centerY).solid || getTile(game.centerX, game.centerY+60).solid) {
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
        int minX = (game.centerX - game.screenSizeX/2 - 32);
        int minY = (game.centerY - game.screenSizeY/2 - 32);
        int maxX = (game.centerX + game.screenSizeX/2 + 32);
        int maxY = (game.centerY + game.screenSizeY/2 + 32);
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
        WallTile wt = new WallTile(game.getTexture("assets/Dungeon Wall Horizontal.png"));
        WallTile wtl = new WallTile(game.getTexture("assets/Dungeon Wall Top Left.png"));
        WallTile wtr = new WallTile(game.getTexture("assets/Dungeon Wall Top Right.png"));
        WallTile wbl = new WallTile(game.getTexture("assets/Dungeon Wall Bottom Left.png"));
        WallTile wbr = new WallTile(game.getTexture("assets/Dungeon Wall Bottom Right.png"));
        WallTile wv = new WallTile(game.getTexture("assets/Dungeon Wall Vertical.png"));
        BlankTile b = new BlankTile();
        ArrayList<Enemy> actors = new ArrayList<>();

        Tile[][] tiles = {
                {wbl, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wbr},
                {wv, f11, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, wv},
                {wv, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, wv},
                {wv, f2, f2, f2, f2, wt, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, wt, f2, f2, f2, wv},
                {wv, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, f1, wv, f1, f1, f1, f1, f1, f1, f1, f1, f1, wv},
                {wv, f3, f3, f3, f3, f31, f3, f3, f3, f3, f3, f3, f3, f3, f3, f3, wv, f3, f3, f3, f3, f3, f3, f3, f3, f3, wv},
                {wv, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f2, f21, wv},
                {wtl, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wt, wtr},
        };
        return new Level(game, tiles, actors);
    }

    public static Level betterLevel(final Teronar game) {
        FloorTile f1 = new FloorTile(game.getTexture("assets/Dungeon Tile 1.png"));
        FloorTile f2 = new FloorTile(game.getTexture("assets/Dungeon Tile 2.png"));
        FloorTile f3 = new FloorTile(game.getTexture("assets/Dungeon Tile 3.png"));
        FloorTile f11 = new FloorTile(game.getTexture("assets/Dungeon Tile 1-1.png"));
        FloorTile f21 = new FloorTile(game.getTexture("assets/Dungeon Tile 2-1.png"));
        FloorTile f31 = new FloorTile(game.getTexture("assets/Dungeon Tile 3-1.png"));
        FloorTile f12 = new FloorTile(game.getTexture("assets/Dungeon Tile 1-2.png"));
        FloorTile f22 = new FloorTile(game.getTexture("assets/Dungeon Tile 2-2.png"));
        FloorTile f32 = new FloorTile(game.getTexture("assets/Dungeon Tile 3-2.png"));
        WallTile wt = new WallTile(game.getTexture("assets/Dungeon Wall Horizontal.png"));
        WallTile wtl = new WallTile(game.getTexture("assets/Dungeon Wall Top Left.png"));
        WallTile wtr = new WallTile(game.getTexture("assets/Dungeon Wall Top Right.png"));
        WallTile wbl = new WallTile(game.getTexture("assets/Dungeon Wall Bottom Left.png"));
        WallTile wbr = new WallTile(game.getTexture("assets/Dungeon Wall Bottom Right.png"));
        WallTile wv = new WallTile(game.getTexture("assets/Dungeon Wall Vertical.png"));
        BlankTile b = new BlankTile();
        ArrayList<Enemy> actors = new ArrayList<>();

        Tile[][] tiles = {
                {wbl, wt,  wt, wbr,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f1, f12,  wv,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f3,  f3, wtl, wbr,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f2,  f2,  f2, wtl, wbr,   b,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f1,  f1,  f1,  f1,  wv,   b,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f3,  f3,  f3,  f3,  wv,   b,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f2,  f2,  f2,  f2, wtl, wbr,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv,  f1,  f1,  f1,  f1,  f1,  wv,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv, f31,  f3,  f3,  f3,  f3,  wv,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wv, f21, f21,  f2,  f2,  f2,  wv,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                {wtl, wt, wbr,  f1,  f1,  f1,  wv,   b,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                { b,   b,  wv,  f3,  f3,  f3, wtl, wbr,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                { b,   b,  wv,  f2,  f2,  f2,  f2,  wv,   b,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                { b,   b,  wv,  f1,  f1,  f1,  f1, wtl, wbr,   b,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                { b,   b,  wv,  f3,  f3,  f3,  f3,  f3, wtl, wbr,   b,   b, wbl,  wt,  wt, wt, wbr,   b,   b},
                { b,   b, wtl, wbr,  f2,  f2,  f2,  f2,  f2,  wv,   b, wbl, wtr, f22,  f2, f2,  wv,   b,   b},
                { b,   b,   b,  wv,  f1,  f1,  f1,  f1,  f1, wtl,  wt, wtr, f11, f11,  f1, f1,  wv,   b,   b},
                { b,   b,   b,  wv,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3, f31,  f3,  f3,wbl, wtr,   b,   b},
                { b,   b, wbl, wtr,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2, wbl,  wt,wtr,   b,   b,   b},
                { b,   b,  wv,  f1,  f1,  f1,  f1,  f1,  f1,  f1, wbl,  wt,  wt, wtr,   b,  b,   b,   b,   b},
                { b,   b,  wv,  f3,  f3,  f3,  f3,  f3,  f3, wbl, wtr,   b,   b,   b,   b,  b,   b,   b,   b},
                { b,   b,  wv,  f2,  f2,  f2,  f2,  f2,  f2,  wv,   b,   b,   b,   b,   b,  b,   b,   b,   b},
                { b, wbl, wtr,  f1,  f1,  f1,  f1,  f1,  f1, wtl, wbr,   b,   b,   b,   b,  b,   b,   b,   b},
                { b,  wv,  f3, f31, f31,  f3,  f3,  f3,  f3,  f3, wtl,  wt, wbr,   b,   b,  b,   b,   b,   b},
                {wbl,wtr,  f2, f21,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  wv,   b,   b,  b,   b,   b,   b},
                {wv,  f1,  f1, f11, wbl,  wt, wbr,  f1,  f1,  f1,  f1,  f1,  wv,   b,   b,  b,   b,   b,   b},
                {wv,  f3,  f3, wbl, wtr,   b, wtl,  f3,  f3,  f3,  f3,  f3,  wv,   b,   b,  b,   b,   b,   b},
                {wv,  f2,  f2,  wv,   b,   b,   b, wbr,  f2,  f2,  f2,  f2,  wv,   b,   b,  b,   b,   b,   b},
                {wv,  f1,  f1,  wv,   b,   b,   b, wtl, wbr,  f1,  f1,  f1, wtl, wbr,   b,  b,   b,   b,   b},
                {wv,  f3, wbl, wtr,   b,   b,   b,   b,  wv,  f3,  f3,  f3,  f3, wtl, wbr,  b,   b,   b,   b},
                {wv,  f2,  wv,   b,   b,   b,   b,   b,  wv,  f2,  f2,  f2,  f2,  f2,  wv,  b,   b,   b,   b},
                {wv,  f1, wtl, wbr,   b,   b,   b,   b, wtl, wbr,  f1,  f1,  f1,  f1,  wv,  b,   b,   b,   b},
                {wv,  f3,  f3, wtl, wbr,   b,   b,   b,   b, wtl, wbr,  f3,  f3,  f3, wtl, wbr,   b,   b,   b},
                {wv,  f2,  f2, f21, wtl, wbr,   b,   b,   b,   b, wtl, wbr,  f2,  f2,  f2,  wv,   b,   b,   b},
                {wv, f11, f11, f11,  f1,  wv,   b,   b,   b,   b,   b,  wv,  f1,  f1,  f1,  wv,  wt,  wt, wbr},
                {wv, f31,  f3,  f3,  f3,  wv,   b,   b,   b,   b,   b, wtl, wbr,  f3,  f3,  wv, f32, f32,  wv},
                {wtl, wbr,  f2, f22, wbl, wtr,   b,   b,   b,   b,   b,   b,  wv,  f2, f21, f21, f21, f22,  wv},
                { b, wtl,  wt,  wt, wtr,   b,   b,   b,   b,   b,   b,   b,  wv,  f1,  f1,  f1,  f1, f11,  wv},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f3,  f3, wbl,  wt,  wt,  wt},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f2,  f2,  wv,   b,   b,   b},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f1,  f1, wtl, wbr,   b,   b},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f3,  f3,  f3,  wv,   b,   b},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f2,  f2,  f2,  wv,   b,   b},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f1,  f1,  f1, wtl, wbr,   b},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f3,  f3,  f3,  f3,  wv,   b},
                { b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f2,  f2,  f2,  f2,  wv,   b},
                { b,   b, wbl,  wt, wbr,   b,   b,   b,   b,   b,   b,   b,  wv,  f1,  f1,  f1,  f1,  wv,   b},
                { b, wbl, wtr, f32, wtl, wbr,   b,   b,   b, wbl,  wt,  wt, wtr,  f3,  f3,  f3, f32,  wv,   b},
                {wbl, wtr, f11, f11,  f1,  wv,   b, wbl,  wt, wtr,  f2,  f2,  f2,  f2,  f2,  f2, f12, wv,   b},
                {wv,  f1, f11,  f1,  f1,  wv, wbl, wtr,  f1,  f1,  f1,  f1,  f1,  f1,  f1, wbl, wt, wtr,   b},
                {wv,  f3,  f3,  f3,  f3, wtl, wtr,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3, wv,   b,   b,   b},
                {wtl, wbr,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2, wtl, wbr,   b,   b},
                { b, wtl, wbr,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1, wtl, wbr,   b},
                { b,   b,  wv,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3, f31,  wv,   b},
                { b,   b,  wv,  f2,  f2,  f2, f21, f21, f21,  f2,  f2,  f2,  f2,  f2,  f2,  f2, f11,  wv,   b},
                { b,   b,  wv,  f1,  f1,  f1, f11, f11,  f1,  f1,  f1,  f1,  f1,  f1,  f1, f11, wbl, wtr,   b},
                { b,   b,  wv,  f3,  f3,  f3,  f3, f31,  f3,  f3,  f3,  f3,  f3,  f3,  f3, wbl, wtr,   b,   b},
                { b,   b,  wv,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  f2,  wv,   b,   b,   b},
                { b,   b,  wv,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  f1,  wv,   b,   b,   b},
                { b,   b,  wv,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  f3,  wv,   b,   b,   b},
                { b,   b,  wv,  f2,  f2,  f2, wbl,  wt,  wt,  wt, wbr,  f2,  f2,  f2,  f2, wtl,  wt,  wt, wbr},
                { b,   b,  wv,  f1,  f1,  f1,  wv,   b,   b,   b, wtl, wbr,  f1,  f1,  f1,  f1, f11, f11,  wv},
                { b, wbl, wtr,  f3,  f3,  f3,  wv,   b,   b,   b,   b, wtl, wbr,  f3,  f3,  f3, f31, f31,  wv},
                {wbl, wtr,  f2,  f2,  f2, wbl, wtr,   b,   b,   b,   b,   b, wtl, wbr,  f2,  f2,  f2, f21,  wv},
                {wv,  f1,  f12, f12,f12,  wv,   b,   b,   b,   b,   b,   b,   b,  wv,  f1,  f1,  f1, f11,  wv},
                {wv,  f3,  f3, f31, f32,  wv,   b,   b,   b,   b,   b,   b,   b,  wv,  f3,  f3,  f3,  f3,  wv},
                {wv, f22,  f2,  f2, f22,  wv,   b,   b,   b,   b,   b,   b,   b, wtl, wbr,  f2,  f2,  f2,  wv},
                {wv, f12, f11,  f1, f12,  wv,   b,   b,   b,   b,   b,   b,   b,   b,  wv,  f1,  f1,  f1,  wv},
                {wtl, wt,  wt,  wt,  wt, wtr,   b,   b,   b,   b,   b,   b,   b,   b, wtl,  wt,  wt,  wt, wtr},
        };
        return new Level(game, tiles, actors);
    }
}
