package pacman.model.gameobjects;

import java.util.ArrayList;
import java.util.Random;


public abstract class Ghost extends Character{


	public Ghost(int x, int y,int width, int height, String anim) {
		super(x, y, width, height,anim);
	}


	@Override
	public abstract void update(float delta);


	ArrayList<directions> possible = new ArrayList<MovingObject.directions>();
	Random rand = new Random();
	
	
	
	/**
	 * this strategy makes the ghost move randomly at each intersection
	 * @param delta
	 */
	public void randomMovement(float delta){
		//first we initialize a random direction
		if(direction == null){
			possibleMovements();
			if(possible.size() > 0){
				direction = possible.get(rand.nextInt(possible.size()));
			}
		}
		
		super.update(delta);
		
		if(isIntersection(x, y)){
			next = possible.get(rand.nextInt(possible.size()));
		}
		else if( ! canMove(direction)){

			possibleMovements();
			next = possible.get(rand.nextInt(possible.size()));
		}
		
	}
	
	/**
	 * return true if the given position is an intersection, append possible directions in {@link Ghost.possible}
	 * @param x
	 * @param y
	 * @return
	 */
	protected boolean isIntersection(int x, int y){
		possibleMovements(x,y);
		if(possible.size() == 2){
			return ! areOnTheSameAxis(possible.get(0), possible.get(1));
		}
		return possible.size()>=3;
	}
	
	
	/**
	 * push each possible direction given a position into {@link Ghost.possible}
	 */
	protected void possibleMovements(int fromX, int fromY){
		possible.clear();
		if(canMove(directions.up,fromX,fromY)){
			possible.add(directions.up);
		}
		if(canMove(directions.down,fromX,fromY)){
			possible.add(directions.down);			
		}
		if(canMove(directions.left,fromX,fromY)){
			possible.add(directions.left);			
		}
		if(canMove(directions.right,fromX,fromY)){
			possible.add(directions.right);			
		}
	}
	
	/**
	 * push each possible direction for the next movement into {@link Ghost.possible}
	 */
	protected void possibleMovements(){
		possibleMovements(x, y);		
	}
	
	public void seekPacman(float delta){
		
	}
	
}
