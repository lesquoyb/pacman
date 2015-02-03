package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class Wormhole extends StaticObject{

	
	public final int id;
	public Wormhole linked = null;
	
	public Wormhole(int x, int y, int width, int height, int id) {
		super(x, y, width, height, ResourceManager.wormhole);
		this.id = id;
	}

}
