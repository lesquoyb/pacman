package pacman.gameobjects;

import pacman.graphics.B2DVars;
import pacman.graphics.TextureFactory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall extends StaticObject{

	//public static final Texture wallTexture = new Texture("images/wall.jpg");
	public static final String name = "wall";
	
	public Wall(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name), B2DVars.wallLayer, B2DVars.maskAll);
	}
	
	@Override
	public void render(SpriteBatch batch){
		//batch.draw(texture, textX, textY);
		bmFont.setColor(Color.BLACK);
		bmFont.draw(batch, "LOOOOOOL", textX,textY);
		//super.render(batch);


	}
	
	
	

}
