package pacman.models;

import com.badlogic.gdx.graphics.Texture;

public class Floor extends StaticObject{

	public static final Texture floorTexture = new Texture("images/floor.jpg");
	
	
	public Floor(byte x, byte y) {
		super(x, y, floorTexture);
	}

	
	
}
