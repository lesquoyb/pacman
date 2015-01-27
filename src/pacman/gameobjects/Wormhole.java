package pacman.gameobjects;

import pacman.graphics.TextureFactory;

public class Wormhole extends StaticObject{

	
	//public static final Texture doorTexture = new Texture("images/wormhole.jpg");
	public static final String name =  "wormhole32";//32 because it's 32*32 pixels
	public final byte id;
	
	public Wormhole(byte x, byte y, byte id) {
		super(x, y, TextureFactory.getTexture(name));
		this.id = id;
	}

}
