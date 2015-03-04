package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.resources.ResourceManager;


public class GreenGhost extends Ghost {



	public GreenGhost(int x, int y,int width, int height,StartingPoint sp) {
		super(x, y, width, height, ResourceManager.GreenGLeft,ResourceManager.GreenGRight,ResourceManager.GreenGUp,ResourceManager.GreenGDown,sp);
	}

	@Override
	public void update(float delta) {
		if(alive){
			seekPosition(delta,GameWorld.getPacman().x,GameWorld.getPacman().y);
		}
		else{
			seekPosition(delta,starting_point.x,starting_point.y);
		}
	}
	

	
}
