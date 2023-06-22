package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Sprites.Tank;

import java.io.*;

public class TankStar extends Game implements Serializable,Menu{
	public SpriteBatch batch;

	public ShapeRenderer shapeRenderer;

	public static int ppm = 100;

	@Override
	public void create() {

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		this.display();
//		this.setScreen(new HomePage(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose(){
		batch.dispose();

	}

	public void serialize() throws IOException {
		ObjectOutputStream out = null;
		try{
			TankStar tankStar = new TankStar();
			out = new ObjectOutputStream(new FileOutputStream("out.txt"));
			out.writeObject(tankStar);
		}catch (IOException e){
			System.out.println("Not serialized");
			System.out.println(e.getMessage());
		}
		finally {
			out.close();
		}

	}

	public void deserialize() throws IOException,ClassNotFoundException{
		ObjectInputStream in = null;
		try{
			in = new ObjectInputStream(new FileInputStream("out.txt"));
			TankStar tankStar = (TankStar) in.readObject();
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
		finally {
			in.close();
		}
	}

	@Override
	public void display() {
		this.setScreen(new HomePage(this));
	}

	@Override
	public void userInput() {

	}
}
