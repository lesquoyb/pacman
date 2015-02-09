package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.Map;

import com.badlogic.gdx.math.Vector2;

public abstract class Character extends MovingObject {

	
	protected Vector2 movement;
	protected directions next;
	protected directions direction;
	protected static final int speed = 100  ;
	protected float remainingPower;
	private boolean alive;
	protected boolean travellingIntoWormhole;
	
	
	public Character(int x, int y,int width, int height, String anim) {
		super(x, y, width, height,anim);
		movement = new Vector2();
		direction = null;
		next = null;
		alive = true;
		remainingPower = speed;
		travellingIntoWormhole = false;
	}

	public boolean isAlive(){return alive;}
	
	public directions getDirection(){return direction;}

	/**
	 * return true if the next grid node is not a wall
	 * @param d
	 * @return
	 */
	protected boolean canMove(directions d){
		return canMove(d,x,y);
		/*
		int newX = x, newY = y;
		switch(d){
			case left:
				newX --;
				break;
				
			case right:
				newX++;
				break;
						
			case up:
				newY--;
				break;
				
			case down:
				newY++;
				break;
			default:
				assert false;
		}
		return !( GameWorld.map.getObstacle(newX * Map.tileWidth, newY * Map.tileHeight) instanceof Wall);
		*/
	}
	

	
	/**
	 * return true if we can move from the given position toward the given direction
	 * @param d
	 * @param fromX
	 * @param fromY
	 * @return
	 */
	protected boolean canMove(directions d, int fromX, int fromY){
		int newX = fromX, newY = fromY;
		switch(d){
			case left:
				if(newX > 0){
					newX --;
				}
				else{
					return false;
				}
				break;
				
			case right:
				if(newX < GameWorld.map.width -1 ){
					newX++;
				}
				else{
					return false;
				}
				break;
						
			case up:
				if(newY > 0 ){
					newY--;
				}
				else{
					return false;
				}
				break;
				
			case down:
				if(newY < GameWorld.map.height - 1){
					newY++;
				}
				else{
					return false;
				}
				break;
			default:
				assert false;
		}
		return !( GameWorld.map.getObstacle(newX * Map.tileWidth, newY * Map.tileHeight) instanceof Wall);
	}

	private float dtX, dtY;
	/**
	 * Try to move to the next grid node using the delta value and the current direction
	 */
	protected void moveToGridPosition(directions d,float delta){
		if(left != x * Map.tileWidth || top != y * Map.tileHeight){			
			switch(d){
				case left:
					dtX = x * Map.tileWidth - left ;
					if( remainingPower*delta > -dtX){
						effectuateMovement(d, dtX,delta);
					}
					else{
						effectuateMovement(d, -remainingPower * delta,delta);
					}
					break;
				case right:
					dtX = x * Map.tileWidth - left ;
					if(remainingPower*delta > dtX){
						effectuateMovement(d, dtX,delta);
					}
					else{
						effectuateMovement(d, remainingPower * delta,delta);
					}
					break;
					
				case up:
					dtY = y * Map.tileWidth - top ;
					if(remainingPower*delta > - dtY){
						effectuateMovement(d, dtY,delta);
					}
					else{
						effectuateMovement(d,- remainingPower * delta,delta);
					}
					break;
				case down:
					dtY = y * Map.tileHeight - top;
					if(remainingPower*delta > dtY){
						effectuateMovement(d, dtY,delta);
					}
					else{
						effectuateMovement(d, remainingPower * delta,delta);
					}
					break;
			}	
		}
	}
	
	/**
	 * Actually move the character with the pixelToMove given as a signed number
	 * @param d
	 * @param force
	 */
	private void effectuateMovement(directions d,float pixelsToMove,float delta){
		switch(d){
			case left:
			case right:
				left += pixelsToMove;
				break;
				
			case up:
			case down:
				top += pixelsToMove;
				break;
		}
		remainingPower -= Math.abs(pixelsToMove)/delta ;
		updatePos();
	}
	
	
	/**
	 * Move toward {@link Character.direction}
	 * Take care of collisions
	 * @param d
	 * @param delta
	 */
	protected void move(float delta){
		
		if(direction != null && remainingPower > 0){
			
			//we try to move to fit the grid node perfectly
			moveToGridPosition(direction, delta);
			wormholesCollisionsCheck();
			
			//if it remains power
			if(remainingPower > 0){
				
				//first we try to move to the next direction, if it's possible we make it the current direction
				if(next != null && next != direction && canMove(next)){
					direction = next;
					next = null;
				}
				if(canMove(direction)){
					switch(direction){
					case left:
						x --;
						break;
					case right:
						x ++;
						break;
					case up:
						y--;
						break;
					case down:
						y++;
						break;
					}
					moveToGridPosition(direction, delta);
					wormholesCollisionsCheck();
				}				
			}
		}

	}
	
	@Override
	protected void updatePos() {
		center.x = left + width/2;
		center.y = top + height/2;
		right = left + width;
		bottom = top + height;
	}
	
	@Override
	public void update(float delta) {
		remainingPower = speed;
		updatePos();
		move(delta);
		
	
	}
	
	
	public void wormholesCollisionsCheck(){
		
		GameObject obstacle = GameWorld.map.getCellFromFloatPosition(center.x, center.y);
		if(obstacle instanceof Wormhole && !travellingIntoWormhole){
			//Teleport
			top = ((Wormhole) obstacle).linked.top;
			left = ( (Wormhole) obstacle).linked.left;
			x = ((Wormhole) obstacle).linked.x;
			y = ((Wormhole) obstacle).linked.y;
			travellingIntoWormhole = true;
			updatePos();
		}
		else if(obstacle instanceof Floor){
			travellingIntoWormhole = false;
		}
	}
	
	
	
}
