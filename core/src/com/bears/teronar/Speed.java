package com.bears.teronar;

public enum Speed {
    ULTRAFAST(100), FAST(100), MEDIUM(100), SLOW(100), ULTRASLOW(100);

    private Speed(final int speed) {
        this.speed = speed;
    }

    private final int speed;

    private int getSpeed() {
        return speed;
    }
}
