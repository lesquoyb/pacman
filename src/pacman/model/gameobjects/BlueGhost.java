package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class BlueGhost extends Ghost{


	
	
	public BlueGhost(int x, int y,int width, int height) {
		super(x, y,width, height, ResourceManager.BlueGLeft, ResourceManager.BlueGRight, ResourceManager.BlueGUp, ResourceManager.BlueGDown);
		
	}

	
	@Override
	public void update(float delta) {
		randomMovement(delta);
	}
	
	
	
	
}
