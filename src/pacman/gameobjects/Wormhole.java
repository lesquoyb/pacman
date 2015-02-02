package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class Wormhole extends StaticObject{

	
	public static final String name =  "wormhole32";//32 because it's 32*32 pixels
	public final int id;
	public Wormhole linked = null;
	
	public Wormhole(int x, int y, int id) {
		super(x, y, TextureFactory.getTexture(name));
		this.id = id;
	}

}
