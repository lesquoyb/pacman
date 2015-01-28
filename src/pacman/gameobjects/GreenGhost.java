package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class GreenGhost extends Ghost {

	
	public static final String name = "pacman";
	
	@Override
	public void update() {
		// TODO IA vert
		
	}

	public GreenGhost(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	
}
