package com.bears.teronar;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Animations {
    private ArrayList<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animations(TextureRegion region, int frameCount, float cycleTime) {
        frames = new ArrayList<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / this.frameCount;
        frame = 0;
    }

    public void update(float delta) {
        currentFrameTime += delta;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount) {
            frame = 0;
        }
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

}
