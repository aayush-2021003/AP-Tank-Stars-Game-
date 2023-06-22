package com.mygdx.game.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.GameObjects;
import com.mygdx.game.TankStar;

public class Weapon extends GameObjects {

    private String name;
    public World world;
    public Body b2body;
    public Body b2body2;
    private int x;
    private int y;

    private final TankStar game;

    private Sprite sprite;




    public Weapon(World world,int x,int y,final TankStar game){

        this.world = world;
        this.game = game;
        this.x=x;
        this.y=y;


    }

    @Override
    public void shoot(float angle, float power, float init_x,float init_y){

        BodyDef bdef = new BodyDef();
        bdef.position.set(init_x,init_y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        this.b2body = this.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(10);

        fdef.shape = shape;
        b2body.createFixture(fdef);



        this.b2body.applyLinearImpulse(new Vector2((float)(power*Math.cos(Math.toRadians(angle))),(float)(power*Math.sin(Math.toRadians(angle)))),this.b2body.getWorldCenter(),true);



    }


    public float getY(float x1,float angle, float power,float init_x,float init_y){
        double y = (x1-init_x)*Math.tan(Math.toRadians(angle))-((0.1*(x1-init_x)*(x1-init_x))/(2*power*Math.cos(Math.toRadians(angle))));
        return (float)y+init_y;
    }

    @Override
    public void shoot2(float angle, float power, float init_x,float init_y){

        BodyDef bdef = new BodyDef();
        bdef.position.set(init_x,init_y);
        bdef.type= BodyDef.BodyType.DynamicBody;
        this.b2body2 = this.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(10);

        fdef.shape = shape;
        this.b2body2.createFixture(fdef);

        this.b2body2.applyLinearImpulse(new Vector2((float)(power*Math.cos(Math.toRadians(angle))),(float)(power*Math.sin(Math.toRadians(angle)))),this.b2body2.getWorldCenter(),true);


    }






}
