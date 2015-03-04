package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.resources.ResourceManager;

public class RedGhost extends Ghost {

	
	public RedGhost(int x, int y, int width, int height,StartingPoint sp) {
		super(x, y, width, height, ResourceManager.RedGLeft, ResourceManager.RedGRight, ResourceManager.RedGUp, ResourceManager.RedGDown,sp);
	}

	@Override
	public void update(float delta) {
		
		if(alive){
			
			if(Ghost.rand.nextInt(6) > 4 ){
				seekPosition(delta,GameWorld.getPacman().x,GameWorld.getPacman().y);
			}
			else{
				randomMovement(delta);
			}
		}
		else{
			seekPosition(delta, starting_point.x, starting_point.y);
		}
		
	}

	
	
}
