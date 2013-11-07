package com.ngochoang.mygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.ngochoang.framework.GameController;
import com.ngochoang.framework.GameMenu;
import com.ngochoang.framework.GamePlayer;
import com.ngochoang.framework.GameBoard;
import com.ngochoang.framework.GameLogic;
import com.ngochoang.framework.GameStatus;

public class MyGdxGame implements ApplicationListener {
	GameLogic framework;
	GameBoard myscene;
	GameController controller;
	GameMenu gui;
	
	@Override
	public void create() {		
		framework = new GameLogic();
		controller = new GameController(framework);
		gui = new GameMenu(framework);
		gui.AddMenuItem("Start Game",GameStatus.GAME_STARTED);
		gui.AddMenuItem("Exit",GameStatus.EXIT_GAME);
		myscene = new GameBoard(framework);
		myscene.SetSize(15);
		framework.setGamescene(myscene);
		framework.setGamecontroller(controller);
		framework.setGamegui(gui);
	}

	@Override
	public void dispose() {
		framework.Release();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		framework.Render();
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
}
