package pacman.model.gameobjects;

import pacman.controller.gamelogic.PacmanGame;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends Character{


	private static final int speed = 100  ;
	//private directions next = null;
	public Pacman(int x, int y) {
		super(x, y, ResourceManager.getPacmanText());
		movement = new Vector2();
	}

	protected void move(directions d) {
		
		direction = d;
	
		switch(direction){
		case down:
			movement.x = 0;
			movement.y =  speed;
			break;
		case up:
			movement.x = 0;
			movement.y = - speed;
			break;
		case left:
			movement.x = - speed ;
			movement.y = 0;
			break;
			
		case right:
			movement.x = speed;
			movement.y = 0;
			break;
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

		

		
		super.update(delta);
	
	}
	

	
}
