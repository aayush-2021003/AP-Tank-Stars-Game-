package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HomePage implements Screen,Menu {
    Texture LoadingScreenImage;
    Skin skin;
    Stage stage;
    final TankStar game;
    TextButton startButton;

    private Music music;


    HomePage(final TankStar game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Metal_UI_Skin/glassy/skin/glassy-ui.json"));
        LoadingScreenImage = new Texture(Gdx.files.internal("loading.png"));

        Gdx.input.setInputProcessor(stage);
        startButton = new TextButton("START",skin,"small");

        music = Gdx.audio.newMusic(Gdx.files.internal("loopmusic.mp3"));

        startButton.setPosition(355,240);
        startButton.setSize(100,50);
        stage.addActor(startButton);

        userInput();

    }

    public void create () {

    }


    @Override
    public void dispose () {
        game.batch.dispose();
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void show() {
//        music.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.batch.draw(LoadingScreenImage,0,0,800,480);
        game.batch.end();


        stage.act();
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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
    public void display() {
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void userInput() {
        startButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                display();
            }

        });
    }
}
