package com.sulf.apo.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sulf.apo.game.Apoc;
import com.sulf.apo.game.Scenes.HUD;

/**
 * Created by Sulf on 2016-02-03.
 */
public class PlayScreen implements Screen{

    private Apoc game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private HUD hud;

    //tiled
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayScreen(Apoc game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Apoc.V_WIDTH, Apoc.V_HEIGHT,gamecam);//StretchViewport() and ScreenViewport()
        hud = new HUD(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("bitmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();
        BodyDef bdef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        Body body;

        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);

            body = world.createBody(bdef);
            polygonShape.setAsBox(rectangle.getWidth() /2, rectangle.getHeight() /2);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);

            body = world.createBody(bdef);
            polygonShape.setAsBox(rectangle.getWidth() /2, rectangle.getHeight() /2);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);

            body = world.createBody(bdef);
            polygonShape.setAsBox(rectangle.getWidth() /2, rectangle.getHeight() /2);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }

        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);

            body = world.createBody(bdef);
            polygonShape.setAsBox(rectangle.getWidth() /2, rectangle.getHeight() /2);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gamecam.position.x += 100 * dt;
        }
    }

    public void update(float dt){
        handleInput(dt);
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        renderer.render();

        b2dr.render(world,gamecam.combined);
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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
