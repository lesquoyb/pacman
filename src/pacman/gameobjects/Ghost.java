package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class Ghost extends Character{

	//public static final Texture ghostTexture = new Texture("images/ghost.png");
	public static final String name = "ghost32";
	public Ghost(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void update() {
		
		// TODO IA des fantomes
		
		updateTextPos();
	}


}
