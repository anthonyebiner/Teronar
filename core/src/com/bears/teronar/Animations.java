package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Animations {
    private ArrayList<TextureRegion> frames;
    private static TextureRegion empty = new TextureRegion(new Texture("./assets/Empty.png"));
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private Boolean playOnce; // This is a boolean that defines if the animation should not repeat.

    public Animations(TextureRegion region, int frameCount, float cycleTime) {
        playOnce = false;
        frames = new ArrayList<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        } // This loop doesn't seem to split the passed texture region at all. It also splits horizontally instead of in a 2x2 grid.
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / this.frameCount;
        frame = 0;
    }

    public void update(float delta) { // Update the animation based on how much time has passed.
        currentFrameTime += delta;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }

        if(frame >= frameCount) { // If the animation is at its end...
            if (playOnce) {
                frame = frameCount + 1; // If the animation should only be played once, keep it at its end.
            } else {
                frame = 0; // Otherwise, restart the animation.
            }
        }
    }

    public TextureRegion getFrame() {
        if (frame == (frameCount + 1) && playOnce){
            return empty; // If the animation is to be played once and has played, return an empty frame.
        } else {
            return frames.get(frame); // Otherwise, return the current frame.
        }

    }

}
