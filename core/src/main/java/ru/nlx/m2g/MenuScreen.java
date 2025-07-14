package ru.nlx.m2g;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    private final Main main;

    public MenuScreen(final Main main) {
        this.main = main;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CLEAR);

        main.viewport.apply(false);
        main.batch.setProjectionMatrix(main.viewport.getCamera().combined);

        main.batch.begin();
            main.font.draw(main.batch, "Hello there", 10, 15);
        main.batch.end();

        if (Gdx.input.isTouched()) {
            main.setScreen(new GameScreen(main));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        main.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
