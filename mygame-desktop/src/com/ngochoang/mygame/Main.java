package com.ngochoang.mygame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ngochoang.controllers.MyGdxGame;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "mygame";
		cfg.useGL20 = false;
		cfg.width = 480;
		cfg.height = 480;
		cfg.resizable = false;
		
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
