package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.PacmanGame;
import pacman.controller.resources.ResourceManager;

public class Pacman extends Character{


	public int eatedGum;
	public int score;


	public Pacman(int x, int y, int width, int height) {
		super(x, y, width, height, ResourceManager.pacmanLeft, ResourceManager.pacmanRight, ResourceManager.pacmanUp, ResourceManager.pacmanDown);
		eatedGum = 0;
		score = 0;
		animated = true;
	}

	private directions directTmp;
	protected void chooseDirection() {
	
		if (PacmanGame.isDownPressed()){
			directTmp = directions.down;
		}
		if (PacmanGame.isLeftPressed()){
			directTmp = directions.left;
		}
		if (PacmanGame.isRightPressed()){
			directTmp = directions.right;
		}
		if (PacmanGame.isUpPressed()){
			directTmp = directions.up;
		}
	
		if(direction == null)
			direction = directTmp ;
		else{
			next = directTmp ;
		}
		
	}

	
	@Override
	public void update(float delta){


		chooseDirection();
		super.update(delta);
		gumCollisionsCheck();
		
	}
	
	private Floor currentPos;
	private void gumCollisionsCheck(){
		
		currentPos = GameWorld.map.getFloor(center.x, center.y);
		if(currentPos != null && currentPos.hasGum()){
			
			boolean res = false;
			
			switch(direction){
				case left:
					res = ( left <= currentPos.center.x);
					break;

				case right:
					res = ( right >= currentPos.center.x);
					break;

				case up:
					res = ( top <= currentPos.center.y);
					break;

				case down:
					res = ( bottom >= currentPos.center.y);
					break;
				default:
					assert false;
			}
			if(res){
				currentPos.setGum(false);
				eatedGum++;
				score++;
			}
		}
	}
	
	
	
}
