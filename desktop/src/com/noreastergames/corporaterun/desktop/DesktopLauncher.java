package com.noreastergames.corporaterun.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.noreastergames.corporaterun.CorporateRunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Corporate Run";
		config.height = 600;
		config.width = 800;
		new LwjglApplication(new CorporateRunGame(), config);
	}
}