package pacman.gameobjects;

import graphics.TextureFactory;

import com.badlogic.gdx.graphics.Texture;

public class Ghost extends Character{

	//public static final Texture ghostTexture = new Texture("images/ghost.png");
	public static final String name = "ghost";
	public Ghost(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void update() {
		// TODO IA des fantomes
		
	}


}
