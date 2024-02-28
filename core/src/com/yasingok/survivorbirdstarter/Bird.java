package com.yasingok.survivorbirdstarter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bird {

    private Texture[] birdTextures;

    // Transition süresini dinamik olarak ayarlamak için eklenen değişken
    private float birdx, birdy, stateTime, transitionDuration, speed, gravity;
    private int currentTextureIndex;

    public Bird(float transitionDuration, String... birdPaths) {
        birdTextures = new Texture[birdPaths.length];

        for (int i = 0; i < birdPaths.length; i++) {
            birdTextures[i] = new Texture(birdPaths[i]);
        }

        birdx = 0;
        birdy = 0;

        stateTime = 0f;
        currentTextureIndex = 0;
        this.transitionDuration = transitionDuration;  // Constructor'da transition süresini ayarla
    }

    public void setPosition(float x, float y) {
        birdx = x;
        birdy = y;
    }

    public void draw(SpriteBatch batch) {
        Texture currentBird = birdTextures[currentTextureIndex];
        batch.draw(currentBird, birdx, birdy, Gdx.graphics.getWidth() / 15, Gdx.graphics.getHeight() / 10);
    }

    public void update(float deltaTime) {
        // Her bir geçiş süresi bittiğinde resimleri değiştirin
        if (stateTime >= transitionDuration) {
            currentTextureIndex = (currentTextureIndex + 1) % birdTextures.length;
            stateTime = 0f;
        }

        // Animasyon süresini güncelle
        stateTime += deltaTime;
    }

    public int getWidth() {
        return birdTextures[currentTextureIndex].getWidth();
    }

    public int getHeight() {
        return birdTextures[currentTextureIndex].getHeight();
    }

    public void dispose() {
        for (Texture texture : birdTextures) {
            texture.dispose();
        }
    }
}