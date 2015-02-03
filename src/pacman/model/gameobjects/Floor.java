package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Floor extends StaticObject{

	
	public Floor(int x, int y) {
		super(x, y, ResourceManager.getFloorText());
	}

	@Override
	public void render(SpriteBatch batch){
		
	}
	
}
