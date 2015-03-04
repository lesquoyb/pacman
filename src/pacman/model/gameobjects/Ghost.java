package pacman.model.gameobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import pacman.controller.gamelogic.GameWorld;


public abstract class Ghost extends Character{


	protected ArrayList<directions> possible = new ArrayList<MovingObject.directions>();
	protected static Random rand = new Random();
	public final StartingPoint starting_point;
	

	public Ghost(int x, int y,int width, int height, String leftAnim,String rightAnim, String upAnim, String downAnim,StartingPoint sp) {
		super(x, y, width, height,leftAnim,rightAnim,upAnim,downAnim,true);
		starting_point = sp;
	}
	

	@Override
	public abstract void update(float delta);

	public void setAlive(boolean alive){
		this.alive = alive;
		switchBehavior();
	}
	
	
	
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
	
	
	public directions directionToReachPosition(int fromX, int fromY,int toReachX, int toReachY){
		
		if(toReachX < fromX && toReachY == fromY){
			return directions.left;
		}
		if(toReachX > fromX && toReachY == fromY){
			return directions.right;
		}
		if(toReachY > fromY && toReachX == fromX){
			return directions.down;
		}
		if(toReachY < fromY && toReachX == fromX){
			return directions.up;
		}
		return null;
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
	
	
	
	protected void collision(Character c){/* on ne fait rien*/	}
	
	/**
	 * push each possible direction for the next movement into {@link Ghost.possible}
	 */
	protected void possibleMovements(){
		possibleMovements(x, y);		
	}
	
	private ArrayList<int[]> path;
	private final int dist_max = 15;
	public void seekPosition(float delta,int toReachX,int toReachY){
		
		
		if(isIntersection(x, y) || path == null || path.size() > dist_max || !canMove(direction) ){
			path = GameWorld.pathfinder.AStar(x, y, toReachX, toReachY);
			if(path != null && path.size() > 0){
				Collections.reverse(path);
				path.remove(0);
			}
		}
		
		
		if(path != null && path.size() <= dist_max){
			
			if(direction == null ){
				direction = directionToReachPosition(x,y, path.get(0)[0], path.get(0)[1]);
				path.remove(0);
			}
			else{
				if(next == null && path.size()>0){
					next = directionToReachPosition(x, y, path.get(0)[0], path.get(0)[1]);
					path.remove(0);
					if(areOnTheSameAxis(next, direction)){
						next = null;
					}
				}
			}
			
			super.update(delta);
		}
		else{
			randomMovement(delta);
		}
		

		
	}
	

	
}
