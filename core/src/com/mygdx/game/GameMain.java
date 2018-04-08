package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import helpers.GameManager;
import scenes.MainMenu;


/**
 * Created by Luca on 08/04/2018.
 */

public class GameMain extends Game {
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		GameManager.getInstance().initializeGameData();
		setScreen(new MainMenu(this));
  	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getBatch(){
		return this.batch;
	}
}
