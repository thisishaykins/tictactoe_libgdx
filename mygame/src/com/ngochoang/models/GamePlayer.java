package com.ngochoang.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ngochoang.controllers.MyGdxGame;
import com.ngochoang.events.PlayerListener;

public class GamePlayer {
	private String name;
	private String sign;
	public boolean[][] checks;
	public SpriteBatch batch;
	public int cellSize;
	PlayerListener listener = null;
	
	public void AddListener(PlayerListener l)
	{
		listener = l;
	}
	
	public GamePlayer(int size) {
		checks = new boolean[size][size];
		for (int row=0;row<size;row++)
			for (int column=0;column<size;column++)
				checks[row][column] = false;
	}

	public void Render() {
		if (MyGdxGame.asset.isLoaded(sign,Texture.class))
		{
			int gap = cellSize/8;
			for (int row=0;row<checks.length;row++)
				for (int column=0;column<checks.length;column++)
				{
					if (checks[row][column])
						batch.draw(MyGdxGame.asset.get(sign, Texture.class), 
								column*cellSize+gap, Gdx.graphics.getHeight() - cellSize - row*cellSize+gap, 
								cellSize-gap*2, cellSize-gap*2);
				}
		}
	}

	public void Release() {
		MyGdxGame.asset.unload(sign);
		System.out.println("Unloaded " + sign);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
		MyGdxGame.asset.load(sign,Texture.class);
		MyGdxGame.asset.finishLoading();
		System.out.println("Loaded " + sign);
	}
	
	public boolean CheckWin()
	{
		for (int row=0;row<checks.length-4;row++)
			for (int column=0;column<checks.length-4;column++)
			{
				if (checks[row][column])
				{
					//check row
					if (checks[row+1][column] && 
							checks[row+2][column] && 
							checks[row+3][column] && 
							checks[row+4][column])
					{
						return true;
					}

					//check column
					if (checks[row][column+1] && 
							checks[row][column+2] && 
							checks[row][column+3] && 
							checks[row][column+4])
					{
						return true;
					}

					//check diagonal down
					if (checks[row+1][column+1] && 
							checks[row+2][column+2] && 
							checks[row+3][column+3] && 
							checks[row+4][column+4])
					{
						return true;
					}

					//check diagonal up
					if (column >= 4)
					{
						if (checks[row+1][column-1] && 
								checks[row+2][column-2] && 
								checks[row+3][column-3] && 
								checks[row+4][column-4])
						{
							return true;
						}
					}
				}
			}
		return false;
	}
	
//	int[] XYToMatrix(int x,int y)
//	{
//		int[] res = new int[2];
//		
//		return res;
//	}
	
	@Override
	public String toString()
	{
		String res = "";
		for (int row=0;row<checks.length;row++)
		{
			for (int column=0;column<checks.length;column++)
				if (checks[row][column])
					res += "x";
				else
					res += "-";
		res += "\n";	
		}
		return res;
	}

	public boolean SetOn(int row, int column) {
		if (checks[row][column] == true)
			return false;
		checks[row][column] = true;
//		System.out.println("checked");
		if (CheckWin())
		{
			if (listener != null)
				listener.PlayerWin(this);
		}
		return true;
	}

	public boolean IsCheckedOn(int row, int column) {
		if (checks[row][column] == true)
			return true;
		return false;
	}

	public void reset() {
		for (int row=0;row<checks.length;row++)
			for (int column=0;column<checks.length;column++)
				checks[row][column] = false;
	}
}
