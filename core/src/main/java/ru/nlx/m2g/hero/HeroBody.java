package ru.nlx.m2g.hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class HeroBody implements Disposable {

    private BodyDef bodyDef;
    private Body body;
    private CircleShape shape;

    private final float acceleration = 50;
    private final float dgarCoff = 0.86f;

    public HeroBody(World world) {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(1000, 500);
        body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        shape = new CircleShape();
        shape.setRadius(6f);

        body.createFixture(shape, 0.5f);
    }

    public void update(int horizontalAxis, int verticalAxis) {
        if (horizontalAxis == 0 && verticalAxis == 0) {
            Vector2 tmp = body.getLinearVelocity();
            body.setLinearVelocity(tmp.x * dgarCoff,
                tmp.y * dgarCoff);
        } else {
            Vector2 move = new Vector2(horizontalAxis,verticalAxis);
            move = move.nor();
            body.setLinearVelocity(move.x * acceleration,
                move.y * acceleration);
        }
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
