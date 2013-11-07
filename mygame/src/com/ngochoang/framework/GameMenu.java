package com.ngochoang.framework;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GameMenu {
	GameLogic logic;
	Vector<String> menuStringItems;
	Vector<GameStatus> menuStatusItems;
	Table table;
	Stage stage;
	Skin skin;

	public GameMenu(GameLogic framework2) {
		menuStringItems = new Vector<String>();
		menuStatusItems = new Vector<GameStatus>();
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		logic = framework2;
        Gdx.input.setInputProcessor(stage);
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		TextButton button = new TextButton("test", skin);
		table.add(button);
	}

	public void Render() {
		if (GameLogic.IsState(GameStatus.GAME_STARTED) == false)
		{
			stage.draw();
			Table.drawDebug(stage);
		}
	}

	public void Release() {
		stage.dispose();
	}

	public void AddMenuItem(String item, GameStatus st) {
		menuStringItems.add(item);
		menuStatusItems.add(st);
	}
}