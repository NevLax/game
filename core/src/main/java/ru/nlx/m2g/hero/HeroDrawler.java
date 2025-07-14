package ru.nlx.m2g.hero;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class HeroDrawler {
    private final int editDrawPosition = 8;

    private float time = 0;
    private Texture texture;
    private HeroTextureRegion textureRegion;
    private Animation<TextureRegion> idle;
    private Animation<TextureRegion> idleInv;
    private Animation<TextureRegion> run;
    private Animation<TextureRegion> runInv;

    public HeroDrawler(Texture texture) {
        this.texture = texture;
        textureRegion = new HeroTextureRegion(texture);

        idle = new Animation<>(0.4f, textureRegion.getIdleRegion());
        idle.setPlayMode(Animation.PlayMode.LOOP);

        idleInv = new Animation<>(0.4f, textureRegion.getIdleInvert());
        idleInv.setPlayMode(Animation.PlayMode.LOOP);

        run = new Animation<>(0.2f, textureRegion.getRunRegion());
        run.setPlayMode(Animation.PlayMode.LOOP);

        runInv = new Animation<>(0.2f, textureRegion.getRunInvert());
        runInv.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void draw(SpriteBatch batch,
                     BitmapFont font,
                     Vector2 pos,
                     float delta,
                     boolean mirror,
                     boolean running,
                     boolean shout
                     ) {
        time += delta;
        font.setColor(Color.BLACK);

        if (running) {
            if (mirror) batch.draw(runInv.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
            else batch.draw(run.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
        } else {
            if (mirror) batch.draw(idleInv.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
            else batch.draw(idle.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
        }

        if (shout) {
            font.draw(batch, "Ahhhhh", pos.x + 10f, pos.y + 10f);
        }
    }

    public void dispose() {
        texture.dispose();
    }
}
