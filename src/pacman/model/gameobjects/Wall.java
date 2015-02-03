package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall extends StaticObject{


	
	public Wall(int x, int y) {
		super(x, y, ResourceManager.getWallText());
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
