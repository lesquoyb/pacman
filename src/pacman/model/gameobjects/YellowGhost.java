package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.resources.ResourceManager;


public class YellowGhost extends Ghost {

	


	public YellowGhost(int x, int y,int width, int height,StartingPoint sp) {
		super(x, y, width, height, ResourceManager.YellowGLeft, ResourceManager.YellowGRight, ResourceManager.YellowGUp, ResourceManager.YellowGDown,sp);
	}

	@Override
	public void update(float delta) {
		// TODO IA Jaune
		if(alive){
			seekPosition(delta, GameWorld.getPacman().x, GameWorld.getPacman().y);
		}
		else{
			seekPosition(delta, starting_point.x, starting_point.y);
		}
	}
	
	
	
}
