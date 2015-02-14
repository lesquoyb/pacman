package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class RedGhost extends Ghost {

	
	public RedGhost(int x, int y, int width, int height) {
		super(x, y, width, height, ResourceManager.RedGLeft, ResourceManager.RedGRight, ResourceManager.RedGUp, ResourceManager.RedGDown);
	}

	@Override
	public void update(float delta) {
		
		
		if(Ghost.rand.nextInt(6) > 4 ){
			seekPacman(delta);
		}
		else{
			randomMovement(delta);
		}
		
	}

	
	
}
