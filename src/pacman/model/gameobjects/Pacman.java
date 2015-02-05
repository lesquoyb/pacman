package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.PacmanGame;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends Character{

	
	private directions next = null;
	public int eatedGum;
	public int score;
	
	
	public Pacman(int x, int y, int width, int height) {
		super(x, y, width, height, ResourceManager.pacman);
		movement = new Vector2();
		eatedGum = 0;
		score = 0;
	}

	protected void move(directions d) {
		if(direction == null)
			direction = d;
		else{
			next = d;
		}
	
	}
	
	
	


	@Override
	public void update(float delta){

		
		
		if (PacmanGame.isDownPressed()){
			move(directions.down);
		}
		if (PacmanGame.isLeftPressed()){
			move(directions.left);
		}
		if (PacmanGame.isRightPressed()){
			move(directions.right);
		}
		if (PacmanGame.isUpPressed()){
			move(directions.up);
		}

		
		if(next != null && next != direction){
			updateMovement(next);
			float left2 = left, top2 = top;
			GameObject obstacle = tryToMove(next, delta);
			left = left2;
			top = top2;
			if(obstacle == null || !( obstacle instanceof Wall) ){
				direction = next;
				next = null;
			}
		}
		
		super.update(delta);
		
		Floor currentPos = GameWorld.map.getFloor(left, top);
		if(currentPos != null && direction != null){
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
				if(currentPos.hasGum()){
					currentPos.setGum(false);
					eatedGum++;
					score++;
				}
			}
		}
	
	}
	

	
}
