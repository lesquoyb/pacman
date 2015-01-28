package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class RedGhost extends Ghost {

	
	public static final String name = "fantomeR";
	
	public RedGhost(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void update() {
		// TODO IA Rouge
		
	}

	
	
}
