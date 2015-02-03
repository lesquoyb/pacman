package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class BlueGhost extends Ghost{



	public BlueGhost(int x, int y) {
		super(x, y, ResourceManager.getBlueGText());
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
