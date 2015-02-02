package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class YellowGhost extends Ghost {

	

	public static final String name = "fantomeJ";

	public YellowGhost(int x, int y) {
		super(x, y, TextureFactory.getTexture(name));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
