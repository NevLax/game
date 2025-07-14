package ru.nlx.m2g.hero;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Hero {
    private HeroDrawler drawler;
    private HeroBody body;
    private MoveState moveState;
    private float shoutTime = 0;

    public Hero(Texture texture, World world, MoveState moveState) {
        drawler = new HeroDrawler(texture);
        body = new HeroBody(world);
        this.moveState = moveState;
    }

    public void update() {
        body.update(moveState.hor, moveState.ver);
    }

    public void draw(SpriteBatch batch, BitmapFont font, float delta) {
        Vector2 pos = body.getPosition();
        boolean inv = body.getLinearVelocity().x < 0;
        boolean running = body.getLinearVelocity().len() > 10f;
        boolean shout = false;
        if (shoutTime > 0) {
            shout = true;
            shoutTime -= delta;
        }

        drawler.draw(batch, font, pos, delta, inv, running, shout);
    }

    public void setShout() {
        shoutTime = 3f;
    }

    public Vector2 getHeroPosition() {
        return body.getPosition();
    }

    public void dispose() {
        drawler.dispose();
    }
}
