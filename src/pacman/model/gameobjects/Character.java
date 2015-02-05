package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;

import com.badlogic.gdx.math.Vector2;

public abstract class Character extends MovingObject {

	protected Vector2 movement;
	protected directions direction;
	private static final int speed = 100  ;
	private boolean alive;
	
	
	public Character(int x, int y,int width, int height, String anim) {
		super(x, y, width, height,anim);
		movement = new Vector2();
		direction = null;
		alive = true;
	}

	public boolean isAlive(){return alive;}
	
	public directions getDirection(){return direction;}
	
	
	private static Vector2 to_test = new Vector2();
	/**
	 * modifie to_test
	 * @param d
	 * @param delta
	 */
	protected void calculateNewPosition(directions d,float delta){
		left += movement.x * delta;
		top += movement.y * delta;
		updatePos();
		to_test.x = left;
		to_test.y = bottom;
		switch(d){
			case left:
				to_test.y = center.y;
				break;
			case right:
				to_test.x = right;
				to_test.y = center.y;
				break;
				
			case up:
				to_test.x = center.x;
				to_test.y = top;
				break;
				
			case down:
				to_test.x = center.x;
				break;
		}
		
	}
		
	
	/**
	 * Attention cette méthode utilise le Vector2 {@link}Character.movement et déplace le personnage
	 * @param d
	 * @return une instance d'objet rencontré
	 */
	protected GameObject tryToMove(directions d,float delta){
		calculateNewPosition(d,delta);
		return   GameWorld.map.getObstacle(to_test.x,to_test.y);	
	}

	
	@Override
	public void update(float delta){
		if(direction != null){
			updateMovement(direction);
			GameObject obstacle = tryToMove(direction, delta);
			if ( obstacle != null && obstacle instanceof Wall){
				switch (direction){
				
					case left:
						left = obstacle.right;
						break;
						
					case right:
						left = obstacle.left - width;
						break;
						
					case up:
						top = obstacle.bottom ;
						break;
						
					case down:
						top = obstacle.top - height;
						break;
				}
			}
			else if(obstacle instanceof Wormhole){
				//Teleport
				top = ((Wormhole) obstacle).linked.top;
				left = ( (Wormhole) obstacle).linked.left;
			}
			
			//dans tous les cas
			updatePos();
	
		}
		
	}
	
	
	
	
	
	protected void updateMovement(directions d){
		switch(d){
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
	
	
	
	
	
	
}
