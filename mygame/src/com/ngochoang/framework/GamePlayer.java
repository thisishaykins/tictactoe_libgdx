package com.ngochoang.framework;

public class GamePlayer {
	GameLogic logic;
	private String name;
	private String sign;
	public boolean[][] checks;
	
	public GamePlayer(GameLogic framework2, int size) {
		logic = framework2;
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
}
