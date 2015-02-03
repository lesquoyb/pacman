package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class RedGhost extends Ghost {

	
	
	public RedGhost(int x, int y) {
		super(x, y, ResourceManager.getRedGText());
	}

	@Override
	public void update(float delta) {
		// TODO IA Rouge
		
	}

	
	
}
