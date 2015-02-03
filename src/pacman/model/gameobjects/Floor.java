package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class Floor extends StaticObject{

	
	public Floor(int x, int y,int width, int height) {
		super(x, y,width, height, ResourceManager.floor);
	}
	
	
}
