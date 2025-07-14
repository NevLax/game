package ru.nlx.m2g.hero;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class HeroInputProcessor implements InputProcessor {

    public Hero hero;
    public MoveState moveState;
    public OrthographicCamera camera;

    public HeroInputProcessor(Hero hero, OrthographicCamera camera, MoveState moveState) {
        this.hero = hero;
        this.camera = camera;
        this.moveState = moveState;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                moveState.hor--;
                break;
            case Input.Keys.D:
                moveState.hor++;
                break;
            case Input.Keys.W:
                moveState.ver++;
                break;
            case Input.Keys.S:
                moveState.ver--;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                moveState.hor++;
                break;
            case Input.Keys.D:
                moveState.hor--;
                break;
            case Input.Keys.W:
                moveState.ver--;
                break;
            case Input.Keys.S:
                moveState.ver++;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldCoord = camera.unproject(new Vector3(screenX, screenY, 0));
        Vector2 heroPos = hero.getHeroPosition();
        if (Math.abs(heroPos.x - worldCoord.x) < 9 &&
            Math.abs(heroPos.y - worldCoord.y) < 9) {
            hero.setShout();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (!(camera.zoom < 0.06f && amountY < 0 ||
              camera.zoom > 2.7f && amountY > 0)) camera.zoom += amountY * 0.05f;
        return false;
    }
}
