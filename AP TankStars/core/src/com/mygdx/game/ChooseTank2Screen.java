package com.mygdx.game;

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

public class ChooseTank2Screen implements Screen {

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

    static String p2choice;



    public TankStar getGame() {
        return game;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getBack() {
        return back;
    }

    public void setBack(TextButton back) {
        this.back = back;
    }

    public TextButton getAbrams() {
        return Abrams;
    }

    public void setAbrams(TextButton abrams) {
        Abrams = abrams;
    }

    public TextButton getCoalition() {
        return Coalition;
    }

    public void setCoalition(TextButton coalition) {
        Coalition = coalition;
    }

    public TextButton getHelios() {
        return Helios;
    }

    public void setHelios(TextButton helios) {
        Helios = helios;
    }

    public Texture getTank1() {
        return tank1;
    }

    public void setTank1(Texture tank1) {
        this.tank1 = tank1;
    }

    public Texture getTank2() {
        return tank2;
    }

    public void setTank2(Texture tank2) {
        this.tank2 = tank2;
    }

    public Texture getTank3() {
        return tank3;
    }

    public void setTank3(Texture tank3) {
        this.tank3 = tank3;
    }



    ChooseTank2Screen(final TankStar game){

        this.game=game;
        skin = GameConstants.skin;
        utility = new Texture(Gdx.files.internal("utility.png"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        com.badlogic.gdx.scenes.scene2d.ui.Label heading = new Label("PLAYER 2",skin,"big");
        heading.setSize(100,100);
        heading.setPosition(Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()-100);
        heading.setAlignment(Align.center);

        stage.addActor(heading);

        back = new TextButton("<<",GameConstants.skin,"small");
        back.setPosition(Gdx.graphics.getWidth()/2-350,Gdx.graphics.getHeight()-100);
        back.setSize(50,50);
        stage.addActor(back);

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new NewGameScreen(game));
            }
        });

        Abrams = new TextButton("Abrams",GameConstants.skin,"small");
        Abrams.setPosition(75,50);
        stage.addActor(Abrams);

        Abrams.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ChooseTank2Screen.p2choice="AbramsRotated.png";
                game.setScreen(new InGameScreen(game));
            }
        });

        Coalition = new TextButton("Coailition",GameConstants.skin,"small");
        Coalition.setPosition(325,50);
        stage.addActor(Coalition);

        Coalition.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ChooseTank2Screen.p2choice="CoalitionRotated.png";
                game.setScreen(new InGameScreen(game));
            }
        });

        Helios = new TextButton("Helios",GameConstants.skin,"small");
        Helios.setPosition(600,50);
        stage.addActor(Helios);

        Helios.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ChooseTank2Screen.p2choice="HeliosRotated.png";
                game.setScreen(new InGameScreen(game));

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
