package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class YellowGhost extends Ghost {

	


	public YellowGhost(int x, int y) {
		super(x, y, ResourceManager.getYellowGText());
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
