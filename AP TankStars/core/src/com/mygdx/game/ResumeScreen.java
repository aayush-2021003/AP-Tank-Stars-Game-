package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jdk.tools.jmod.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ResumeScreen extends TankStar implements Screen {

    private final TankStar game;
    private Skin skin;
    private Stage stage;
    private TextButton Resume;
    private TextButton Load;
    private TextButton Exit;

    private InGameScreen screen;

    ResumeScreen(final TankStar game, final InGameScreen screen){
        this.game = game;
        this.screen = screen;
        skin = GameConstants.skin;
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        com.badlogic.gdx.scenes.scene2d.ui.Label heading = new Label("PAUSE SCREEN",skin,"big");
        heading.setSize(100,100);
        heading.setPosition(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-100);
        heading.setAlignment(Align.center);

        stage.addActor(heading);

        Resume = new TextButton("Resume",GameConstants.skin,"small");
        Resume.setPosition(400,300);
        Resume.setSize(70,70);
        stage.addActor(Resume);



        Load = new TextButton("Load",GameConstants.skin,"small");
        Load.setPosition(400,200);
        Load.setSize(70,70);
        stage.addActor(Load);



        Exit = new TextButton("Exit",GameConstants.skin,"small");
        Exit.setPosition(400,100);
        Exit.setSize(70,70);
        stage.addActor(Exit);






    }


    @Override
    public void show() {
        Resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(screen);
            }
        });

        Load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    ResumeScreen.super.serialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                game.setScreen(screen);
            }
        });

        Exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.627f, 0.125f, 0.941f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
