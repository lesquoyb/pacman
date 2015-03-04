package pacman.model.gameobjects;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.Map;

public abstract class Character extends MovingObject {

	
	protected directions next;
	protected directions direction;
	protected static final int speed = 275  ;
	protected double remainingPower;
	protected boolean alive;
	public boolean eater;
	protected boolean travellingIntoWormhole;
	public String leftAnim;
	public String rightAnim;
	public String upAnim;
	public String downAnim;
	public String leftDead;
	public String rightDead;
	public String upDead;
	public String downDead;	
	
	public Character(int x, int y,int width, int height, String leftAnim, String rightAnim, String upAnim, String downAnim,boolean eater) {
		super(x, y, width, height,downAnim);
		direction = null;
		next = null;
		alive = true;
		remainingPower = speed;
		travellingIntoWormhole = false;
		animated = true;
		this.leftAnim = leftAnim;
		this.rightAnim = rightAnim;
		this.upAnim = upAnim;
		this.downAnim = downAnim;
		leftDead = rightAnim;
		rightDead = leftAnim;
		upDead = downAnim;
		downDead = upAnim;
		
	}
	
	
	private void switchAnim(){
		String animTmp = leftAnim;
		leftAnim = leftDead;
		leftDead = animTmp;
		
		animTmp = rightAnim;
		rightAnim = rightDead;
		rightDead = animTmp;
		
		animTmp = upAnim;
		upAnim = upDead;
		upDead = animTmp;
		
		animTmp = downAnim;
		downAnim = downDead;
		downDead = animTmp;
		
	}
	
	public void switchBehavior(){
		//switchAnim();
		eater = ! eater;
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
	}
	
	
	

	


	private GameObject currentPos;
	/**
	 * return true if we can move from the given position toward the given direction
	 * @param d
	 * @param fromX
	 * @param fromY
	 * @return
	 */
	protected boolean canMove(directions d, int fromX, int fromY){
		int newX = fromX, newY = fromY;

		currentPos = GameWorld.map.getElement(newX, newY);
		if(currentPos instanceof Wormhole && !travellingIntoWormhole){
			newX = ( (Wormhole) currentPos).linked.x;
			newY = ( (Wormhole) currentPos).linked.y;
		}

		switch(d){
			case left:
				if(newX > 0 ){
					newX --;
				}
				else{
					return false;
				}
				break;
				
			case right:
				if(newX < GameWorld.map.width -1){
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
	
	protected int[] positionAfterMovement(directions d, int newX, int newY){
		currentPos = GameWorld.map.getElement(newX, newY);
		if(currentPos instanceof Wormhole && !travellingIntoWormhole){
			newX = ( (Wormhole) currentPos).linked.x;
			newY = ( (Wormhole) currentPos).linked.y;
		}

		int[] ret = {newX,newY};
		switch(d){
			case left:
				if(newX > 0 ){
					ret[0] --;
				}
				else{
					return null;
				}
				break;
				
			case right:
				if(newX < GameWorld.map.width -1){
					ret[0]++;
				}
				else{
					return null;
				}
				break;
						
			case up:
				if(newY > 0 ){
					ret[1]--;
				}
				else{
					return null;
				}
				break;
				
			case down:
				if(newY < GameWorld.map.height - 1){
					ret[1]++;
				}
				else{
					return null;
				}
				break;
			default:
				assert false;
		}
		return ret;
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
	private void effectuateMovement(directions d,double pixelsToMove,float delta){
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
				if(next != null && canMove(next)){
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
		right = left +width;
		bottom = top + Map.tileHeight;
	}
	
	Character character;
	@Override
	public void update(float delta) {
		
		remainingPower = speed;
		
		updatePos();
		
		move(delta);
		
		chooseAnimation();
		
		updatePos();
	
	}
	
	protected byte i;
	protected void checkCollision(){
		for( i = 0 ; i < GameWorld.characters.size();i++){
			character = GameWorld.characters.get(i);
			if (character != this){
				if(    ((left >= character.left && left <= character.right) || (right <= character.right && right >= character.left))
					&& ((top <= character.top && bottom >= character.top) || (top >= character.top && top <= character.bottom ))
					){
					collision(character);
				}
			}
			
		}
	}
	
	protected abstract void collision(Character c);
	
	private void chooseAnimation(){
		if(direction != null){
			switch(direction){
			case left:
				animation = leftAnim;
				break;
				
			case right:
				animation = rightAnim;
				break;
				
			case up:
				animation = upAnim;
				break;
				
			case down:
				animation = downAnim;
				break;
			}
		}
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
