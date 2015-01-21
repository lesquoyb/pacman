package pacman.models;

import com.badlogic.gdx.graphics.Texture;

public class Wormhole extends StaticObject{

	
	public static final Texture doorTexture = new Texture("images/door.png");
	
	public final byte id;
	
	public Wormhole(byte x, byte y, byte id) {
		super(x, y, doorTexture);
		this.id = id;
	}

}
