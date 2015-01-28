package pacman.gameobjects;

import pacman.graphics.B2DVars;
import pacman.graphics.TextureFactory;

public class Wall extends StaticObject{

	//public static final Texture wallTexture = new Texture("images/wall.jpg");
	public static final String name = "wall";
	
	public Wall(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name), B2DVars.wallLayer, B2DVars.maskAll);
		fixtureDef.friction = 0;
	}

}
