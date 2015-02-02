package pacman.gameobjects;

import pacman.graphics.TextureFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Floor extends StaticObject{

	public static final String name = "floor";
	
	public Floor(int x, int y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	@Override
	public void render(SpriteBatch batch){
		
	}
	
}
