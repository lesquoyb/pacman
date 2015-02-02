package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class RedGhost extends Ghost {

	
	public static final String name = "fantomeR";
	
	public RedGhost(int x, int y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void update(float delta) {
		// TODO IA Rouge
		
	}

	
	
}
