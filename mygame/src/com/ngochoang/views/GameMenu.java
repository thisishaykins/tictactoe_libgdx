package com.ngochoang.views;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ngochoang.events.GameMenuListener;

public class GameMenu extends Stage implements InputProcessor {
	Vector<String> menuStringItems;
	Vector<TextButton> buttons;
	Table table;
	Skin skin;
	GameMenuListener listener = null;
	
	public void AddListener(GameMenuListener l)
	{
		listener = l;
	}

	public GameMenu() {
		buttons = new Vector<TextButton>();
		menuStringItems = new Vector<String>();

		skin = new Skin(Gdx.files.internal("uiskin.json"));
		table = new Table();
		table.setFillParent(true);
		this.addActor(table);
	}

	public void Render() {
		this.draw();
	}

	public void Release() {
	}

	public void AddMenuItem(String item) {
		menuStringItems.add(item);

		TextButton bt = new TextButton(item, skin);
		bt.setName(item);
		table.add(bt);
		bt.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				int index = menuStringItems.indexOf(e.getListenerActor()
						.getName());
				if (listener != null)
					listener.OnMenuItemClicked(index);
			}
		});
		table.row();
	}
}