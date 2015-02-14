package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class Wall extends StaticObject{


	
	public Wall(int x, int y,int width, int height) {
		super(x, y, width, height, ResourceManager.wall);
	}
	
	/*
	
	@Override
	public void render(SpriteBatch batch){
		//batch.draw(texture, textX, textY);
		//bmFont.setColor(Color.BLACK);
		//bmFont.draw(batch, "LOOOOOOL", textX,textY);
		//System.out.println(textX + " " + textY +" " + x + " " + y);

		super.render(batch);


	}
	
	*/
	
	

}
