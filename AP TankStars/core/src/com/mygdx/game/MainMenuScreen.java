package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import jdk.tools.jmod.Main;

public class MainMenuScreen implements Screen {

    final TankStar game;
    Texture MainMenuImage;
    
    Skin skin;
    Stage stage;
    TextButton NewGame;
    TextButton ResumeGame;
    TextButton ExitGame;
    Table table;



    MainMenuScreen(final TankStar game){

        this.game = game;
        MainMenuImage = new Texture(Gdx.files.internal("homepage.jpg"));
        skin = new Skin(Gdx.files.internal("Metal_UI_Skin/glassy/skin/glassy-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        NewGame = new TextButton("NEW GAME",skin,"small");
        ResumeGame = new TextButton("RESUME",skin,"small");
        ExitGame = new TextButton("EXIT",skin,"small");

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        NewGame.setSize(100,50);
        table.add(NewGame).pad(10).expandX().right();

        ResumeGame.setSize(100,50);
        table.row();
        table.add(ResumeGame).pad(10).expandX().right();

        ExitGame.setSize(100,50);
        table.row();
        table.add(ExitGame).pad(10).expandX().right();


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.627f, 0.125f, 0.941f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(MainMenuImage,0,0,600,480);
        game.batch.end();

        NewGame.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new NewGameScreen(game));
            }

        });

        ResumeGame.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new LoadGameScreen(game));
            }

        });

        ExitGame.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }

        });

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
    public void dispose() {
        game.batch.dispose();
        skin.dispose();
        stage.dispose();
    }
}
