package com.sulf.apo.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    public PlayScreen(Apoc game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Apoc.V_WIDTH, Apoc.V_HEIGHT,gamecam);//StretchViewport() and ScreenViewport()
        hud = new HUD(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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
