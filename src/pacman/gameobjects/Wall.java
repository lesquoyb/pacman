package pacman.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public class Wall extends StaticObject{

	public static final Texture wallTexture = new Texture("images/wall.jpg");

	
	public Wall(byte x, byte y) {
		super(x, y, wallTexture);
	}

}
