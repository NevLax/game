package ru.nlx.m2g.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class HeroTextureRegion {
    private final TextureRegion[][] all;

    public HeroTextureRegion(Texture texture) {
        all = TextureRegion.split(texture, 16, 16);
    }

    public TextureRegion[] getIdleRegion() {
        TextureRegion[] idle = new TextureRegion[2];
        idle[0] = all[2][0];
        idle[1] = all[2][1];
        return idle;
    }

    public TextureRegion[] getRunRegion() {
        TextureRegion[] run = new TextureRegion[4];
        run[0] = all[2][4];
        run[1] = all[2][1];
        run[2] = all[2][2];
        run[3] = all[2][3];
        return run;
    }

    public TextureRegion[] getRunInvert() {
        TextureRegion[] run = new TextureRegion[4];
        run[0] = all[3][4];
        run[1] = all[3][1];
        run[2] = all[3][2];
        run[3] = all[3][3];
        return run;
    }

    public TextureRegion[] getIdleInvert() {
        TextureRegion[] idle = new TextureRegion[2];
        idle[0] = all[3][0];
        idle[1] = all[3][1];
        return idle;
    }
}
