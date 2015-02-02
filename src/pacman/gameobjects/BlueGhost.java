package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class BlueGhost extends Ghost{


	public static final String name = "fantomeB";

	public BlueGhost(int x, int y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
