package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class GreenGhost extends Ghost {



	public GreenGhost(int x, int y,int width, int height) {
		super(x, y, width, height, ResourceManager.GreenGLeft,ResourceManager.GreenGRight,ResourceManager.GreenGUp,ResourceManager.GreenGDown);
	}

	@Override
	public void update(float delta) {
		
		seekPacman(delta);
	}
	

	
}
