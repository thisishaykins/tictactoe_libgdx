package com.ngochoang.models;

public class GamePlayer {
	private String name;
	private String sign;
	public boolean[][] checks;
	
	public GamePlayer(int size) {
		checks = new boolean[size][size];
		for (int i=0;i<size;i++)
			for (int j=0;j<size;j++)
				checks[i][j] = false;
	}

	public void Render() {
	}

	public void Release() {
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
	}
	
	int[] XYToMatrix(int x,int y)
	{
		int[] res = new int[2];
		
		return res;
	}

	public boolean SetOn(int screenX, int screenY) {
		return false;
	}
}
