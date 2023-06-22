package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;

public class NewGameScreen implements Screen {

    final TankStar game;
    private Skin skin;
    private Stage stage;
    private TextButton back;
    private TextButton Abrams;
    private TextButton Coalition;
    private TextButton Helios;
    Texture tank1;
    Texture tank2;
    Texture tank3;
    Texture utility;
    static String p1choice;


    NewGameScreen(final TankStar game){

        this.game = game;
        skin = GameConstants.skin;
        stage = new Stage(new ScreenViewport());
        utility = new Texture(Gdx.files.internal("utility.png"));
        Gdx.input.setInputProcessor(stage);
        Label heading = new Label("PLAYER 1",skin,"big");
        heading.setSize(100,100);
        heading.setPosition(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-100);
        heading.setAlignment(Align.center);

        stage.addActor(heading);

        back = new TextButton("<<",GameConstants.skin,"small");
        back.setSize(50,50);
        back.setPosition(Gdx.graphics.getWidth()/2-350,Gdx.graphics.getHeight()-100);
        stage.addActor(back);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Abrams = new TextButton("Abrams",GameConstants.skin,"small");
        Abrams.setPosition(75,50);
        stage.addActor(Abrams);

        Abrams.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                NewGameScreen.p1choice="Abrams.png";
                game.setScreen(new ChooseTank2Screen(game));
            }
        });

        Coalition = new TextButton("Coailition",GameConstants.skin,"small");
        Coalition.setPosition(325,50);
        stage.addActor(Coalition);

        Coalition.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                NewGameScreen.p1choice="Coalition.png";
                game.setScreen(new ChooseTank2Screen(game));
            }
        });

        Helios = new TextButton("Helios",GameConstants.skin,"small");
        Helios.setPosition(600,50);
        stage.addActor(Helios);

        Helios.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                NewGameScreen.p1choice="Helios.png";
                game.setScreen(new ChooseTank2Screen(game));
            }
        });

        tank1= new Texture(Gdx.files.internal("Abrams.png"));
        tank2= new Texture(Gdx.files.internal("Coalition.png"));
        tank3= new Texture(Gdx.files.internal("Helios.png"));


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.627f, 0.125f, 0.941f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(utility,0,0,800,480);
        game.batch.draw(tank1,75,150,156,128);
        game.batch.draw(tank2,325,150,156,128);
        game.batch.draw(tank3,600,150,156,128);
        game.batch.end();

        stage.act();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
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
        game.batch.dispose();
    }
}
