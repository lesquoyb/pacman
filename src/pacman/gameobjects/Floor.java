package pacman.gameobjects;

import graphics.TextureFactory;

public class Floor extends StaticObject{

	public static final String name = "floor";
	
	public Floor(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	
	
}
