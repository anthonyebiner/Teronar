package com.bears.teronar;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position defaultPos() {
        return new Position(0, 0);
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
