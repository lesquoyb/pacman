package pacman.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pacman.graphics.B2DVars;
import pacman.graphics.TextureFactory;

public class Floor extends StaticObject{

	public static final String name = "floor";
	
	public Floor(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name), B2DVars.floorLayer,B2DVars.maskNothing);
	}

	@Override
	public void render(SpriteBatch batch){
		
	}
	
}
