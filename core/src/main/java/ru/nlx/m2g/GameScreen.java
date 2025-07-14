package ru.nlx.m2g;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.nlx.m2g.hero.Hero;
import ru.nlx.m2g.hero.HeroInputProcessor;

public class GameScreen implements Screen {
    private final Main main;
    private Hero hero;
    private HeroInputProcessor heroInputProcessor;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Texture ground;

    public GameScreen(final Main main) {
        this.main = main;
        world = new World(Vector2.Zero, true);
        ground = new Texture("ground.jpg");
        hero = new Hero(new Texture(
            Gdx.files.internal("MiniWorldSprites/Characters/Workers/FarmerTemplate.png")),
            world);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(
            main.viewport.getScreenWidth(),
            main.viewport.getScreenHeight());
        heroInputProcessor = new HeroInputProcessor(hero, camera);
        Gdx.input.setInputProcessor(heroInputProcessor);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GRAY);
        hero.update();
        world.step(Math.min(delta, 0.2f), 6, 2);

        followHero(delta);
        camera.update();
        main.batch.setProjectionMatrix(camera.combined);

        main.batch.begin();
            main.batch.draw(ground, ground.getWidth() / -2f, ground.getHeight() / -2f);
            hero.draw(main.batch, delta);
        main.batch.end();

        debugRenderer.render(world, camera.combined);
    }

    public void followHero(float delta) {
        float factor = 3f;

        camera.position.lerp(new Vector3(hero.getHeroPosition(), 0), factor * delta);
//        camera.position.set(hero.getHeroPosition(), 0);
    }

    @Override
    public void resize(int width, int height) {
        main.viewport.update(width, height);
        camera.viewportHeight = 240f;
        camera.viewportWidth = 240f  * width/height;
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
        hero.dispose();
    }
}
