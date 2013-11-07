package com.ngochoang.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameBoard{
	ShapeRenderer shape;
	GameLogic logic;
	int tableSize;
	int size;
	GamePlayer[] players;
	
	public GameBoard(GameLogic framework)
	{
		players = new GamePlayer[2];
		for (int i=0;i<players.length;i++)
		{
			players[i] = new GamePlayer(logic,size);
			players[i].setName("Player " + (i+1));
			if (i == 0)
				players[i].setSign("X");
			else
				players[i].setSign("O");
		}
		shape = new ShapeRenderer();
		this.logic = framework;
		tableSize = Gdx.graphics.getWidth();
	}
	
	public void Render() {
		if (GameLogic.IsState(GameStatus.GAME_STARTED) == true)
		{
			int cellSize = tableSize / size;
			shape.begin(ShapeType.Line);
			shape.setColor(Color.BLACK);
			for (int i = cellSize; i <= tableSize; i += cellSize) {
				shape.line(i, 0, i, tableSize);
				shape.line(0, i, tableSize, i);
			}
			shape.end();
		}
	}

	public void Release() {
		shape.dispose();
	}
	
	public void SetSize(int size)
	{
		this.size = size;
	}
}
