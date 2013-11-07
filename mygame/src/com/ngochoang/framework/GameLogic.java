package com.ngochoang.framework;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class GameLogic {
	public static HashMap<GameStatus, Boolean> states = new HashMap<GameStatus, Boolean>();
	private GameBoard gamescene;
	private GameController gamecontroller;
	private GameMenu gamegui;
	
	public static boolean IsState(GameStatus state)
	{
		if (states.containsKey(state) == false)
			return false;
		return states.get(state);
	}
	
	public static void SetState(GameStatus state, boolean flag)
	{
		if (states.containsKey(state) == false)
			states.put(state, flag);
	}

	public void Render()
	{
		getGamescene().Render();
		getGamecontroller().Render();
		getGamegui().Render();
	}

	public void Release() {
		getGamescene().Release();
		getGamecontroller().Release();
		getGamegui().Release();
	}

	public GameBoard getGamescene() {
		return gamescene;
	}

	public void setGamescene(GameBoard gamescene) {
		this.gamescene = gamescene;
		getGamescene().logic = this;
	}

	public GameController getGamecontroller() {
		return gamecontroller;
	}

	public void setGamecontroller(GameController gamecontroller) {
		this.gamecontroller = gamecontroller;
		getGamecontroller().logic = this;
	}

	public GameMenu getGamegui() {
		return gamegui;
	}

	public void setGamegui(GameMenu gamegui) {
		this.gamegui = gamegui;
		getGamegui().logic = this;
	}
}
