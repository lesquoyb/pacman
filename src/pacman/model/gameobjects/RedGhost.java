package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class RedGhost extends Ghost {

	
	public RedGhost(int x, int y, int width, int height) {
		super(x, y, width, height, ResourceManager.RedG);
	}

	@Override
	public void update(float delta) {
		// TODO IA Rouge
		
	}

	
	
}
