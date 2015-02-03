package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class GreenGhost extends Ghost {

	


	public GreenGhost(int x, int y) {
		super(x, y, ResourceManager.getGreenGText());
	}

	@Override
	public void update(float delta) {
		// TODO IA vert
		
	}
	
}
