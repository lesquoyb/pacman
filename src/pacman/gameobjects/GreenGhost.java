package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class GreenGhost extends Ghost {

	
	public static final String name = "fantomeV";
	


	public GreenGhost(int x, int y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void update(float delta) {
		// TODO IA vert
		
	}
	
}
