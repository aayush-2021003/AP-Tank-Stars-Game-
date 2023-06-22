package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Sprites.Tank;

public class LoadGameScreen implements Screen {

    private final TankStar game;
    private TextButton back;

    private Stage stage;
    private TextButton tile;

    public LoadGameScreen(final TankStar game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Label heading = new Label("Loading Screen",GameConstants.skin,"big");
        heading.setSize(100,100);
        heading.setPosition(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-100);
        heading.setAlignment(Align.center);

        stage.addActor(heading);

        back = new TextButton("<<",GameConstants.skin,"small");
        back.setPosition(Gdx.graphics.getWidth()/2-350,Gdx.graphics.getHeight()-100);
        back.setSize(50,50);
        stage.addActor(back);

        tile = new TextButton("Game 1",GameConstants.skin,"small");
        tile.setPosition(100,250);
        tile.setSize(100,50);
        stage.addActor(tile);

        TextButton tile2 = new TextButton("Game 2",GameConstants.skin,"small");
        tile2.setPosition(300,250);
        tile2.setSize(100,50);
        stage.addActor(tile2);


        TextButton tile3 = new TextButton("Game 3",GameConstants.skin,"small");
        tile3.setPosition(500,250);
        tile3.setSize(100,50);
        stage.addActor(tile3);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        tile3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                game.setScreen(new InGameScreen(game));
            }
        });

        tile2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                game.setScreen(new InGameScreen(game));
            }
        });

        tile.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new InGameScreen(game));
            }
        });

    }

    @Override
    public void show() {

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
