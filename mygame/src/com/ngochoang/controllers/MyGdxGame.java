package com.ngochoang.controllers;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.ngochoang.views.GameBoard;
import com.ngochoang.views.GameMenu;

public class MyGdxGame implements ApplicationListener {
	public enum GameStatus {
		GAME_STARTED, EXIT_GAME, SHOW_CREDIT, MAIN_MENU
	}

	public static HashMap<GameStatus, Boolean> states = new HashMap<GameStatus, Boolean>();
	GameBoard vBoard;
	GameMenu vMenu;
	InputMultiplexer multiplexer;

	@Override
	public void create() {
		multiplexer = new InputMultiplexer();
		vBoard = new GameBoard();
		vMenu = new GameMenu();
		multiplexer.addProcessor(vBoard);
		multiplexer.addProcessor(vMenu);

		vMenu.AddMenuItem("Start game");
		vMenu.AddMenuItem("Exit");
	}

	public static boolean IsState(GameStatus state) {
		if (states.containsKey(state) == false)
			return false;
		return states.get(state);
	}

	public static void SetState(GameStatus state, boolean flag) {
		Gdx.app.log("Hoang", "Set " + state + " to " + flag);
		states.put(state, flag);
	}

	public static void ToggleState(GameStatus key) {
		SetState(key, !IsState(key));
	}

	@Override
	public void dispose() {
		vBoard.Release();
		vMenu.Release();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		vMenu.Render();
		vBoard.Render();
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
