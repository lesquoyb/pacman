package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class BlueGhost extends Ghost{


	
	
	public BlueGhost(int x, int y,int width, int height,StartingPoint sp) {
		super(x, y,width, height, ResourceManager.BlueGLeft, ResourceManager.BlueGRight, ResourceManager.BlueGUp, ResourceManager.BlueGDown,sp);
		
	}

	
	@Override
	public void update(float delta) {
		if(alive){
			randomMovement(delta);	
		}
		else{
			seekPosition(delta, starting_point.x, starting_point.y);
		}
		
	}
	
	
	
	
}
