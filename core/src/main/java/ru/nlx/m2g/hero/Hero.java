package ru.nlx.m2g.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Hero {
    private Texture texture;
    private HeroTextureRegion textureRegion;
    private Animation<TextureRegion> idle;
    private Animation<TextureRegion> idleInv;
    private Animation<TextureRegion> run;
    private Animation<TextureRegion> runInv;
    private int editDrawPosition = 8;
    private float time = 0;
    private HeroBody body;

    public boolean Up = false;
    public boolean Down = false;
    public boolean Right = false;
    public boolean Left = false;

    public Hero(Texture texture, World world) {
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

        body = new HeroBody(world);
    }

    public void update() {
        int ver = 0;
        int hor = 0;

        if (Up) ver++;
        if (Down) ver--;
        if (Right) hor++;
        if (Left) hor--;

        body.update(hor, ver);
    }

    public void draw(SpriteBatch batch, float delta) {
        time += delta;
        Vector2 pos = body.getPosition();
        boolean inv = body.getLinearVelocity().x < 0;

        if (body.getLinearVelocity().len() > 10f) {
            if (inv) batch.draw(runInv.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
            else batch.draw(run.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
        } else {
            if (inv) batch.draw(idleInv.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
            else batch.draw(idle.getKeyFrame(time, true), pos.x - editDrawPosition, pos.y - editDrawPosition);
        }
    }

    public Vector2 getHeroPosition() {
        return body.getPosition();
    }

    public void dispose() {
        texture.dispose();
    }
}
