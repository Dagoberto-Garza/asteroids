package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.command.*;
import com.mygdx.game.states.*;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	GameStateManager gsm;
	public final static int HIGHT = 900;
	public final static int WIDTH = 1800;
	public final static float ft=1f/60f;
	public static ArrayList<Command> queue= new ArrayList<>();
	public static BitmapFont font;

	@Override
	public void create () {
		Gdx.graphics.setTitle("Asteroids");
		Gdx.graphics.setWindowedMode(WIDTH,HIGHT);
		font =new BitmapFont();
		batch = new SpriteBatch();
		gsm= new GameStateManager();

		//gsm.push(new GameState());
		gsm.push(new MenuState(gsm));
		//gsm.push(new AsteroidState(gsm));
		//gsm.push(new GameState());
		queue.add(new UpComm());
		queue.add(new DownComm());
		queue.add(new LeftComm());
		queue.add(new RightComm());
		queue.add(new ShootComm());
	}

	@Override
	public void render () {
		update();
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
	public void update(){

	}



}
