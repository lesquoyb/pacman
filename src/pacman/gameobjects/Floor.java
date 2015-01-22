package pacman.gameobjects;

import graphics.TextureFactory;

import com.badlogic.gdx.graphics.Texture;

public class Floor extends StaticObject{

	//public static final Texture floorTexture = new Texture("images/floor.jpg");
	public static final String name = "floor";
	
	public Floor(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	
	
}
