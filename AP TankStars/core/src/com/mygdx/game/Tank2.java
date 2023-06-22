package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Sprites.Weapon;
import com.mygdx.game.Sprites.Weapon;

import java.util.ArrayList;
import java.util.Iterator;

public class Tank2 extends Sprite {
    public World world;
    public Body b2body;
    private int x;
    private int y;

    private TankStar game;

    private Sprite sprite;

    private String tankchosen;

    private com.mygdx.game.Sprites.Weapon bullet;

    private ArrayList<Float> x_coordinates = new ArrayList<>();

    private int health=100;

    private int fuel=100;

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private static Tank2 tank=null;

    public static Tank2 getInstanceOf(World world, int x, int y, InGameScreen screen, TankStar game
            , String tankchosen){
        if (tank==null){
            tank = new Tank2(world, x, y, screen, game,tankchosen);
        }

        return tank;
    }


    private Tank2(World world, int x, int y, InGameScreen screen, TankStar game
            , String tankchosen) {

        this.world = world;
        this.x=x;
        this.y=y;
        this.game=game;
        this.tankchosen=tankchosen;
        this.bullet = new Weapon(world,x,y,game);
        defineTank();
    }

    public void defineTank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(10);

        fdef.shape = shape;
        b2body.createFixture(fdef);



    }

    public void setImage(float x,float y){

        Texture img = new Texture(Gdx.files.internal(this.tankchosen));
        sprite = new Sprite(img);
        sprite.setPosition(x-20,y-15);
        sprite.setSize(64,64);
        game.batch.begin();
        sprite.draw(game.batch);
        game.batch.end();
    }

    public void setProjectile(float angle, float power,float init_x,float init_y){

        float ctr=10;
        Texture imag = new Texture(Gdx.files.internal("whitedot.png"));
        Sprite sprite = new Sprite(imag);
//        Texture img = new Texture(Gdx.files.internal("bullet.png"));
//        sprite = new Sprite(img);

        for (int i = 0; i < 10; i++) {
            x_coordinates.add(ctr+init_x);
            ctr+=10;
        }

        for (int i=0;i<10;i++){
            float y= getY(init_x+ctr,angle,power,init_x,init_y);
            sprite.setSize(10,10);
            sprite.setPosition(init_x+ctr,y+30);
            game.batch.begin();
            sprite.draw(game.batch);
            game.batch.end();


            ctr+=10;
        }



    }


    public void setProjectile2(float angle, float power,float init_x,float init_y){

        float ctr=10;
        Texture imag = new Texture(Gdx.files.internal("whitedot.png"));
        Sprite sprite = new Sprite(imag);
        for (int i=0;i<10;i++){
            float y= getY(init_x-ctr,angle,power,init_x,init_y);
            sprite.setSize(10,10);
            sprite.setPosition(init_x-ctr,y+30);
            game.batch.begin();
            sprite.draw(game.batch);
            game.batch.end();


            ctr+=10;
        }

        for (int i = 0; i < 10; i++) {
            x_coordinates.add(ctr-init_x);
            ctr+=10;
        }

        Iterator<Float> iter = x_coordinates.iterator();
        while (iter.hasNext()){
//            System.out.println(iter.next());
            float y= getY(iter.next(), angle,power,init_x,init_y);
            sprite.setSize(10,10);
            sprite.setPosition(iter.next(), y+550);
            game.batch.begin();
            sprite.draw(game.batch);
            game.batch.end();
        }


    }



    public float getY(float x1,float angle, float power,float init_x,float init_y){
        double y = (x1-init_x)*Math.tan(Math.toRadians(angle))-((0.1*(x1-init_x)*(x1-init_x))/(2*power*Math.cos(Math.toRadians(angle))));
        return (float)y+init_y;
    }

    public void shoot(float angle,float power,float x,float y){
        this.bullet.shoot(angle,power,x,y);
    }

    public void shoot2(float angle,float power,float x,float y){
        this.bullet.shoot2(angle,power,x,y);
    }
}
