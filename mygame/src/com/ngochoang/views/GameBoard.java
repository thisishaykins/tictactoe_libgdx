package com.ngochoang.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ngochoang.events.GameBoardListener;

public class GameBoard implements InputProcessor {
	private int nbCell = 5;
	private int tableSize;
	ShapeRenderer shape = new ShapeRenderer();
	GameBoardListener listener = null;

	public void AddListener(GameBoardListener l) {
		listener = l;
	}

	public void Render() {
		tableSize = Gdx.graphics.getHeight();
		int cellSize = tableSize / nbCell;
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
		for (int i = cellSize; i <= tableSize; i += cellSize) {
			shape.line(i, 0, i, tableSize);
			shape.line(0, i, tableSize, i);
		}
		shape.end();
	}

	public void Release() {
		shape.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
}
