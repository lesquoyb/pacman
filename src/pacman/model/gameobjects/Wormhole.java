package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;

public class Wormhole extends StaticObject{

	
	public final int id;
	public Wormhole linked = null;
	
	public Wormhole(int x, int y, int id) {
		super(x, y, ResourceManager.getWormholeText());
		this.id = id;
	}

}
