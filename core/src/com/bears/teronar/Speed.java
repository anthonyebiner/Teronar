package com.bears.teronar;

public enum Speed {
    ULTRAFAST(100), FAST(75), MEDIUM(50), SLOW(25), STOPPED(0);

    private Speed(final int speed) {
        this.speed = speed;
    }

    private final int speed;

    private int getSpeed() {
        return speed;
    }
}
