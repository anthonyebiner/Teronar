package com.bears.teronar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Animations {
    public TextureRegion[] frames;
    private static TextureRegion empty = new TextureRegion(new Texture("./assets/Empty.png"));
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private Boolean playOnce; // This is a boolean that defines if the animation should not repeat.

    public Animations(TextureRegion region, int frameCount, float cycleTime) {
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / this.frameCount;
        this.playOnce = false;
        this.frame = 0;
        TextureRegion[][] tmp = TextureRegion.split(region.getTexture(),
                region.getTexture().getWidth() / 2,
                region.getTexture().getHeight() / 2);
        frames = new TextureRegion[frameCount];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                frames[index] = tmp[i][j];
                System.out.println(frames[index]);
                index++;
            }
        }

    }

    public void update(float delta) { // Update the animation based on how much time has passed.
        currentFrameTime += delta;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }

        if(frame >= frameCount) { // If the animation is at its end...
            if (playOnce) {
                frame = frameCount - 1; // If the animation should only be played once, keep it at its end.
            } else {
                frame = 0; // Otherwise, restart the animation.
            }
        }
    }

    public TextureRegion getFrame() {
        if (frame == (frameCount - 1) && playOnce){
            return this.frames[frame]; // return empty; // If the animation is to be played once and has played, return an empty frame.
        } else {
            return this.frames[frame]; // Otherwise, return the current frame.
        }

    }

}
