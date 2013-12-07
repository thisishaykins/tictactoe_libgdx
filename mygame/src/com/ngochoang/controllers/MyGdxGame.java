package com.ngochoang.controllers;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ngochoang.events.GameBoardListener;
import com.ngochoang.events.GameMenuListener;
import com.ngochoang.events.PlayerListener;
import com.ngochoang.models.GamePlayer;
import com.ngochoang.views.GameBoard;
import com.ngochoang.views.GameMenu;

public class MyGdxGame implements ApplicationListener, InputProcessor, PlayerListener{
	public enum GameStatus {
		GAME_STARTED, EXIT_GAME, SHOW_CREDIT, MAIN_MENU
	}

	SpriteBatch batch;
	public static HashMap<GameStatus, Boolean> states = new HashMap<GameStatus, Boolean>();
	public static AssetManager asset = new AssetManager();
	GameBoard vBoard;
	GameMenu vMenu;
	InputMultiplexer multiplexer;
	GamePlayer p1;
	GamePlayer p2;
	int currentTurn = 1;

	@Override
	public void create() {		
		batch = new SpriteBatch();
		multiplexer = new InputMultiplexer();
		vBoard = new GameBoard();
		vBoard.SetNbCell(16);
		vMenu = new GameMenu();
		p1 = new GamePlayer(vBoard.GetNbCell());
		p2 = new GamePlayer(vBoard.GetNbCell());
		p1.setSign("data/player_icons/cross.png");
		p2.setSign("data/player_icons/circle.png");
		p1.batch = batch;
		p2.batch = batch;
		p1.cellSize = vBoard.getCellSize();
		p2.cellSize = vBoard.getCellSize();
		p1.AddListener(this);
		p2.AddListener(this);
		p1.setName("Player 1");
		p2.setName("Player 2");
		
		multiplexer.addProcessor(vMenu);
		multiplexer.addProcessor(this);

		Gdx.input.setInputProcessor(multiplexer);

		vMenu.AddListener(new GameMenuListener() {

			@Override
			public void OnMenuItemClicked(int index) {
				switch (index) {
				case 0:
					if (IsState(GameStatus.GAME_STARTED) == false)
					{
						MyGdxGame.ToggleState(GameStatus.GAME_STARTED);
						multiplexer.removeProcessor(vMenu);
						multiplexer.addProcessor(vBoard);
					}
					break;
				case 1:
					Gdx.app.exit();
				}
			}
		});
		
		vBoard.AddListener(new GameBoardListener() {
			
			@Override
			public void CellClick(int row, int column) {
				System.out.println("player click on: "+row+","+column);
				if (currentTurn == 1)
				{
					if (p1.SetOn(row, column) && p2.IsCheckedOn(row,column) == false)
					{
						currentTurn = 2;
						System.out.println("Debug p1");
						System.out.println(p1.toString());
					}
					else
					{
						System.out.println("Error");
					}
				}
				else
				{
					if (p2.SetOn(row, column) && p1.IsCheckedOn(row,column) == false)
					{
						currentTurn = 1;
						System.out.println("Debug p2");
						System.out.println(p2.toString());
					}
					else
					{
						System.out.println("Error");
					}
				}
			}
		});
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
		p1.Release();
		p2.Release();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();

		if (IsState(GameStatus.GAME_STARTED) == false || IsState(GameStatus.MAIN_MENU))
			vMenu.Render();
		if (IsState(GameStatus.GAME_STARTED) == true)
		{
			vBoard.Render();
			p1.Render();
			p2.Render();
		}
		batch.end();
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.ESCAPE && IsState(GameStatus.GAME_STARTED))
		{
			ToggleState(GameStatus.MAIN_MENU);
			if (IsState(GameStatus.MAIN_MENU))
				multiplexer.addProcessor(vMenu);
			else
				multiplexer.removeProcessor(vMenu);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void PlayerWin(GamePlayer p) {
		System.out.println(p.getName() + " is winner");
		p1.reset();
		p2.reset();
	}
}
