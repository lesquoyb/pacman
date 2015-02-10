package pacman.model.gameobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.utils.Array;

import pacman.controller.gamelogic.GameWorld;


public abstract class Ghost extends Character{


	protected ArrayList<directions> possible = new ArrayList<MovingObject.directions>();
	protected static Random rand = new Random();
	

	public Ghost(int x, int y,int width, int height, String anim) {
		super(x, y, width, height,anim);
//		tmp = new ArrayList<int[]>();
//		visited = new ArrayList<int[]>();
	}


	@Override
	public abstract void update(float delta);


	
	
	
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
	
	
	
	
	
	
	
	
	
	/**
	 * push each possible direction for the next movement into {@link Ghost.possible}
	 */
	protected void possibleMovements(){
		possibleMovements(x, y);		
	}
	
	
	public void seekPacman(float delta){
		
		ArrayList<int[]> path = GameWorld.pathfinder.AStar(x, y, GameWorld.getPacman().x, GameWorld.getPacman().y);
		if(path != null && path.size() > 1){
			Collections.reverse(path);
			path.remove(0);
		}
		if(direction == null){
			direction = directionToReachPosition(x,y, path.get(0)[0], path.get(0)[1]);
			path.remove(0);
		}
		else{
			if(next == null && path.size()>0){
				next = directionToReachPosition(x, y, path.get(0)[0], path.get(0)[1]);
				path.remove(0);
			}
		}
		
		super.update(delta);
		
		/*
		if(oldPacmanPos == null){
			oldPacmanPos = new int[]{GameWorld.getPacman().x,GameWorld.getPacman().y};
		}
		if (   		GameWorld.getPacman().x != oldPacmanPos[0] 
				|| GameWorld.getPacman().y != oldPacmanPos[1]
				|| (tmp.size() == 0 && (oldPacmanPos[0] != x || oldPacmanPos[1] != y ) )  ){
			
			oldPacmanPos = new int[]{GameWorld.getPacman().x,GameWorld.getPacman().y};
			tmp = findPacman();

		}
		if(tmp != null && tmp.size()>0){
			if(direction == null){
				direction = directionToReachPosition(tmp.get(0)[0], tmp.get(0)[1]);
//				tmp.remove(0);
			}
			else if (x == tmp.get(0)[0] && y == tmp.get(0)[1] && tmp.size() > 1){
				next = directionToReachPosition(tmp.get(1)[0], tmp.get(1)[1]);
				tmp.remove(0);
			}
		}
		super.update(delta);

		*/
		
	}
	

	
}
