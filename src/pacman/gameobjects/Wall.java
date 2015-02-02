package pacman.gameobjects;

import pacman.graphics.TextureFactory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall extends StaticObject{


	public static final String name = "wall";
	
	public Wall(int x, int y) {
		super(x, y, TextureFactory.getTexture(name));
	}
	
	@Override
	public void render(SpriteBatch batch){
		//batch.draw(texture, textX, textY);
		//bmFont.setColor(Color.BLACK);
		//bmFont.draw(batch, "LOOOOOOL", textX,textY);
		//System.out.println(textX + " " + textY +" " + x + " " + y);

		super.render(batch);


	}
	
	
	

}
