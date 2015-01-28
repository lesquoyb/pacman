package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class BlueGhost extends Ghost{


	public static final String name = "fantomeB";

	public BlueGhost(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
