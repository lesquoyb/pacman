package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends MovingObject {

	protected Vector2 movement;
	protected directions direction;

	public Character(int x, int y, Texture texture) {
		super(x, y, texture);
		movement = new Vector2();
		direction = null;

	}

	
	
	public directions getDirection(){return direction;}
	
	
	protected boolean canMove(directions d){
		switch(d){
			case up:
				return !( GameWorld.map.getObstacle(left, bottom - height) instanceof Wall);
				
			case down:
				return !( GameWorld.map.getObstacle(left, bottom + height) instanceof Wall);
				
			case left:
				return ! (GameWorld.map.getObstacle(left - width, top ) instanceof Wall);
				
			case right:
				return ! (GameWorld.map.getObstacle(left + width, top) instanceof Wall);
		
		}
		return false;
	}
	
	@Override
	public void update(float delta){
		
		if(direction != null){		
			left += movement.x * delta;
			top += movement.y * delta;
			updatePos();
			float to_testX = left, to_testY = bottom;
			switch(direction){
				case left:
					to_testY = center.y;
					break;
				case right:
					to_testX = right;
					to_testY = center.y;
					break;
					
				case up:
					to_testX = center.x;
					to_testY = top;
					break;
					
				case down:
					to_testX = center.x;
					break;
			}

			GameObject obstacle  = GameWorld.map.getObstacle(to_testX,to_testY);
			if ( obstacle != null ){
				if(obstacle instanceof Wall){
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
					top = ((Wormhole) obstacle).linked.top;
					left = ( (Wormhole) obstacle).linked.left;
				}
				
				//dans tous les cas
				updatePos();
				
			}
		}
	
	}
}
