package ru.nlx.m2g.hero;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class HeroInputProcessor implements InputProcessor {

    public Hero hero;
    public OrthographicCamera camera;

    public HeroInputProcessor(Hero hero, OrthographicCamera camera) {
        this.hero = hero;
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                hero.Left = true;
                break;
            case Input.Keys.D:
                hero.Right = true;
                break;
            case Input.Keys.W:
                hero.Up = true;
                break;
            case Input.Keys.S:
                hero.Down = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                hero.Left = false;
                break;
            case Input.Keys.D:
                hero.Right = false;
                break;
            case Input.Keys.W:
                hero.Up = false;
                break;
            case Input.Keys.S:
                hero.Down = false;
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
        System.out.println("worldCoord = " + worldCoord);
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
