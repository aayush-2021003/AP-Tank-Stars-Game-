package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Sprites.Tank;

public class GameOverScreen implements Screen {


    private final TankStar game;
    private TextButton GameOver;
    private String winner;

    private Stage stage;
    private Skin skin;

    Label won;
    Label heading;

    InGameScreen ingamescreen;

    GameOverScreen(final TankStar game, String winner, final InGameScreen ingamescreen){
        this.game=game;
        this.winner=winner;

        this.ingamescreen = ingamescreen;

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Metal_UI_Skin/glassy/skin/glassy-ui.json"));

        Gdx.input.setInputProcessor(stage);


        heading = new Label("GAME OVER",skin,"big");
        heading.setSize(50,50);
        heading.setPosition(400, 400);
        heading.setAlignment(Align.center);
        stage.addActor(heading);

        won = new Label(this.winner+" Won",skin,"big");
        won.setSize(50,50);
        won.setPosition(400, 300);
        won.setAlignment(Align.center);

        stage.addActor(won);


        TextButton Restart = new TextButton("Restart",skin,"small");
        Restart.setPosition(350,200);
        Restart.setSize(100,50);
        stage.addActor(Restart);



        GameOver = new TextButton("Exit",skin,"small");
        GameOver.setPosition(350,100);
        GameOver.setSize(100,50);
        stage.addActor(GameOver);

        GameOver.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.setScreen(new MainMenuScreen(game));
            }

        });

        Restart.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Tank p1 = ingamescreen.getP1();
                p1.setHealth(100);

                Tank2 p2 = ingamescreen.getP2();
                p2.setHealth(100);
                game.setScreen(new InGameScreen(game));
            }
        });


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0.627f, 0.125f, .9415f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    }
}
