package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class YellowGhost extends Ghost {

	


	public YellowGhost(int x, int y,int width, int height) {
		super(x, y, width, height, ResourceManager.YellowG);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}