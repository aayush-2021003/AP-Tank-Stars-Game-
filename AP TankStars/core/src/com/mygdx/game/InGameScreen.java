package com.mygdx.game;

import Scenes.Hud;
import Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Sprites.Weapon;
import com.mygdx.game.Sprites.*;

import java.util.ArrayList;
import java.util.Iterator;


public class InGameScreen implements Screen {

    private final TankStar game;
    private Stage stage;
    private Skin skin;


    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Hud hud;
    private OrthographicCamera gamecam;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Tank p1;
    private Tank2 p2;

    private Texture Tank1Image;
    private TextureAtlas atlas;

    private TextButton Fire;
    private TextButton pause;

    private int angle=45;
    private int power=50;

    private int angle2=135;


    private int power1=50;
    private int power2=50;

    private Sprite health;



    private boolean flag=true;

    private Sprite sprite;

    boolean flagfired=false;

    InGameScreen screen = this;

    Coordinates c;

    Texture fuel;
    Texture healthimg;


    public int getAngle() {
        return angle;
    }

    public int getPower() {
        return power;
    }

    InGameScreen(final TankStar game){
        this.game=game;

        c = new Coordinates();



        skin = new Skin(Gdx.files.internal("Metal_UI_Skin/glassy/skin/glassy-ui.json"));
        this.stage=new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        hud = new Hud(game.batch);

        fuel = new Texture(Gdx.files.internal("fuel.png"));
        sprite = new Sprite(fuel);
        Label fuellabel = new Label("Fuel",skin,"black");
        fuellabel.setPosition(50,60);
        fuellabel.setSize(5,5);
        stage.addActor(fuellabel);

        Label fuellabel2 = new Label("Fuel",skin,"black");
        fuellabel2.setPosition(560,60);
        fuellabel2.setSize(5,5);
        stage.addActor(fuellabel2);

        Label power1 = new Label("Power1",skin,"black");
        power1.setPosition(35,10);
        power1.setSize(5,5);
        stage.addActor(power1);

        Label power2 = new Label("Power2",skin,"black");
        power2.setPosition(530,10);
        power2.setSize(5,5);
        stage.addActor(power2);


        healthimg = new Texture(Gdx.files.internal("healthimg.png"));
        health = new Sprite(healthimg);

        mapLoader = new TmxMapLoader();
        this.map = mapLoader.load("tankstarterrain.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false,Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        world = new World(new Vector2(0,-10),true);
        b2dr=new Box2DDebugRenderer();


        p1 = Tank.getInstanceOf(world,32,120,this,this.game,NewGameScreen.p1choice);
        p2 = Tank2.getInstanceOf(world,750,200,this,this.game,ChooseTank2Screen.p2choice);




        new B2WorldCreator(world,map);

    }

    public Tank getP1() {
        return p1;
    }

    public Tank2 getP2() {
        return p2;
    }

    @Override
    public void show() {


    }


    public void update(float dt){
        world.step(1/60f,6,2);
//        handleInput((dt));
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {

        if (p1.getHealth()<=0 && p2.getHealth()>0){
            //p1 lost
            game.setScreen(new GameOverScreen(game,"Player 2",this));
        }
        else if (p2.getHealth()<=0 && p1.getHealth()>0){
            //p2 lost
            game.setScreen(new GameOverScreen(game,"Player 1",this));
        }

        Sprite powerbar = new Sprite(new Texture(Gdx.files.internal("yellow.jpg")));


        Fire = new TextButton("FIRE",GameConstants.skin,"small");
        Fire.setPosition(400,64);
        Fire.setSize(50,50);
        stage.addActor(Fire);

        pause=new TextButton("||",GameConstants.skin,"small");
        pause.setPosition(370,420);
        pause.setSize(50,50);
        stage.addActor(pause);

        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ResumeScreen(game,screen));
            }
        });


        Fire.addListener(new ChangeListener() {

//            public float getY(float x1,float angle, float power,float init_x,float init_y){
//                double y = (x1-init_x)*Math.tan(Math.toRadians(angle))-((0.1*(x1-init_x)*(x1-init_x))/(2*power*Math.cos(Math.toRadians(angle))));
//                return (float)y+init_y;
//            }

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (flag==true){
                    p1.setFuel(100);
                    power1=100;
                    p1.shoot(angle,power1,p1.b2body.getPosition().x+20,p1.b2body.getPosition().y+20);
                }
                else{
                    p2.setFuel(100);
                    power2=100;
                    p2.shoot2(angle2,power2,p2.b2body.getPosition().x-20,p2.b2body.getPosition().y+20);

                }

                flag=!flag;

            }
        });


        if (flag==true){
            if (Gdx.input.isKeyPressed(Input.Keys.D) && p1.b2body.getLinearVelocity().x<=10){
                if (p1.getFuel()<0){
                    p1.b2body.setLinearVelocity(new Vector2(0f,0));
                    p2.b2body.setLinearVelocity(new Vector2(0,0));
                }
                else{
                    p1.setFuel(p1.getFuel()-5);
                    p1.b2body.applyLinearImpulse(new Vector2(8.5f,0),p1.b2body.getWorldCenter(),true);
                    p2.b2body.setLinearVelocity(new Vector2(0,0));

                }

            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
                p1.setHealth(p1.getHealth()-10);
//                world.destroyBody(Weapon.b2body);

            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.G)){
                p1.setHealth(p1.getHealth()-5);
//                world.destroyBody(Weapon.b2body);

            }


            if (Gdx.input.isKeyPressed(Input.Keys.A) && p1.b2body.getLinearVelocity().x>=-10){

                if (p1.getFuel()<0){
                    p1.b2body.setLinearVelocity(new Vector2(0f,0));
                    p2.b2body.setLinearVelocity(new Vector2(0,0));
                }
                else{
                    p1.setFuel(p1.getFuel()-5);
                    p1.b2body.applyLinearImpulse(new Vector2(-8.5f,0),p1.b2body.getWorldCenter(),true);
                    p2.b2body.setLinearVelocity(new Vector2(0,0));

                }


            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
//                System.out.println("w is pressed");
                if(this.angle>=10 && this.angle<=80){
                    this.angle-=10;
                }
                else{
                    this.angle=45;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
//                System.out.println("s is pressed");
                if(this.angle>=10 && this.angle<=80){
                    this.angle+=10;
                }
                else{
                    this.angle=45;
                }
            }
            if(!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)){
                p1.b2body.setLinearVelocity(new Vector2(0f, 0f));
                p2.b2body.setLinearVelocity(new Vector2(0f, 0f));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.Z)){
                if (power1>100){
                    power1=100;
                }
                else{
                    power1+=10;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.X)){
                if (power1<50){
                    power1=100;
                }
                else{
                    power1-=10;
                }
            }
        }
        else{
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && p2.b2body.getLinearVelocity().x<=10){
                if (p2.getFuel()<0){
                    p1.b2body.setLinearVelocity(new Vector2(0,0));
                    p2.b2body.setLinearVelocity(new Vector2(0,0));

                }
                else{
                    p2.setFuel(p2.getFuel()-5);
                    p1.b2body.setLinearVelocity(new Vector2(0,0));
                    p2.b2body.applyLinearImpulse(new Vector2(8.5f,0),p2.b2body.getWorldCenter(),true);

                }

            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.B)){
                p2.setHealth(p2.getHealth()-10);

            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.V)){
                p2.setHealth(p2.getHealth()-5);


            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && p2.b2body.getLinearVelocity().x>=-10){
                if (p2.getFuel()<0){
                    p1.b2body.setLinearVelocity(new Vector2(0,0));
                    p2.b2body.setLinearVelocity(new Vector2(0,0));

                }
                else{
                    p2.setFuel(p2.getFuel()-5);
                    p1.b2body.setLinearVelocity(new Vector2(0,0));
                    p2.b2body.applyLinearImpulse(new Vector2(-8.5f,0),p2.b2body.getWorldCenter(),true);

                }



            }
            if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                p1.b2body.setLinearVelocity(new Vector2(0f, 0f));
                p2.b2body.setLinearVelocity(new Vector2(0f, 0f));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
//                System.out.println("w is pressed");
                if(this.angle2>=90 && this.angle2<=180){
                    this.angle2-=10;
                }
                else{
                    this.angle2=135;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
//                System.out.println("s is pressed");
                if(this.angle2>=90 && this.angle2<=180){
                    this.angle2+=10;
                }
                else{
                    this.angle2=135;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.N)){
                if (power2>100){
                    power2=50;
                }
                else{
                    power2+=10;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.M)){
                if (power2<50){
                    power2=50;
                }
                else{
                    power2-=10;
                }
            }


        }




        update(delta);



        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(gamecam);
        renderer.render();



        b2dr.render(world,gamecam.combined);


        Coordinates.setInit_x(p1.b2body.getPosition().x);
        Coordinates.setInit_y(p1.b2body.getPosition().y);

        p1.setImage(Coordinates.getInit_x(),Coordinates.getInit_y());
        p2.setImage(p2.b2body.getPosition().x,p2.b2body.getPosition().y);

        p1.setProjectile(this.angle,50,p1.b2body.getPosition().x,p1.b2body.getPosition().y);
        p2.setProjectile2(angle2,50,p2.b2body.getPosition().x,p2.b2body.getPosition().y);


        if (flag==false && Math.abs(p1.b2body.getPosition().x-p2.b2body.getPosition().x)<5){
            p2.setHealth(p2.getHealth()-20);
        }

        if (flag==false && Math.abs(p1.b2body.getPosition().x-p2.b2body.getPosition().x)<10){
            p2.setHealth(p2.getHealth()-10);
        }

        if (flag==false && Math.abs(p1.b2body.getPosition().x-p2.b2body.getPosition().x)<15){
            p2.setHealth(p2.getHealth()-5);
        }

        if (flag==true && Math.abs(p2.b2body.getPosition().x-p1.b2body.getPosition().x)<5){
            p1.setHealth(p1.getHealth()-20);
        }

        if (flag==true && Math.abs(p2.b2body.getPosition().x-p1.b2body.getPosition().x)<10){
            p1.setHealth(p1.getHealth()-10);
        }

        if (flag==true && Math.abs(p2.b2body.getPosition().x-p1.b2body.getPosition().x)<15){
            p1.setHealth(p1.getHealth()-5);
        }


        if (p1.getFuel()>0){
            sprite.setSize(p1.getFuel(),30);
            sprite.setPosition(100,50);

            game.batch.begin();
            sprite.draw(game.batch);
            game.batch.end();

        }

        if (p2.getFuel()>0){
            sprite.setSize(p2.getFuel(),30);
            sprite.setPosition(600,50);

            game.batch.begin();
            sprite.draw(game.batch);
            game.batch.end();

        }


        if (p1.getHealth()>0) {
            health.setSize(p1.getHealth(), 30);

            health.setPosition(150, 420);

            game.batch.begin();
            health.draw(game.batch);
            game.batch.end();
        }


        if(p2.getHealth()>0) {


            health.setSize(p2.getHealth(), 30);

            health.setPosition(550, 420);

            game.batch.begin();
            health.draw(game.batch);
            game.batch.end();

        }


        if (power1>=50 && power1<=100){
            powerbar.setSize(power1,10);
            powerbar.setPosition(100,10);
            game.batch.begin();
            powerbar.draw(game.batch);
            game.batch.end();

        }

        if (power2>=50 && power2<=100){
            powerbar.setSize(power2,10);
            powerbar.setPosition(600,10);
            game.batch.begin();
            powerbar.draw(game.batch);
            game.batch.end();

        }



        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        stage.act();
        stage.draw();


    }

    public float getY(float x1,float angle, float power,float init_x,float init_y){
        double y = (x1-init_x)*Math.tan(Math.toRadians(angle))-((0.1*(x1-init_x)*(x1-init_x))/(2*power*Math.cos(Math.toRadians(angle))));
        return (float)y+init_y;
    }

    @Override
    public void resize(int width, int height) {
        gamecam.viewportWidth=width;
        gamecam.viewportHeight=height;
        gamecam.position.set(width/2f, height/2f, 0);
        gamecam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
//        game.batch.dispose();
//        map.dispose();
//        renderer.dispose();
//        b2dr.dispose();
//        hud.dispose();
    }


}
