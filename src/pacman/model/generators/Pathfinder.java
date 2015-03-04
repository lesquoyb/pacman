package pacman.model.generators;

import java.util.ArrayList;
import java.util.HashMap;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.Map;
import pacman.controller.gamelogic.Map.gameElements;
import pacman.model.gameobjects.Floor;
import pacman.model.gameobjects.GameObject;
import pacman.model.gameobjects.MovingObject.directions;
import pacman.model.gameobjects.Wall;
import pacman.model.gameobjects.Wormhole;

public class Pathfinder {


	
	private Map.gameElements[][] matrix;
	private ArrayList<int[]> total_path;
	private ArrayList<Position> neighborsTmp;
	
	public Pathfinder(ArrayList<ArrayList<GameObject>> grid){
		transformToMatrix(grid);
		total_path =  new ArrayList<int[]>();
		neighborsTmp = new ArrayList<Position>(4);
	}
	
	
	private void transformToMatrix(ArrayList<ArrayList<GameObject>> grid){
		matrix = new Map.gameElements[grid.size()][grid.get(0).size()];
		int x = 0 , y = 0;
		for(ArrayList<GameObject> list : grid){
			for(GameObject object : list){
				if(object instanceof Floor){
					matrix[x][y] = gameElements.floor;
				}
				else if(object instanceof Wall){
					matrix[x][y] = gameElements.wall;
				}
				else{
					assert false;
				}				
				y++;
			}
			y = 0;
			x++;
		}
	}
	
	
	
	private class Position{
		public int x, y;
		public Position(int x, int y){this.x = x; this.y = y;}
		public int[] toIntArray(){return new int[]{x,y};}
		
		@Override
		public boolean equals(Object arg0) {
			if(arg0 instanceof Position){
				return ((Position)arg0).x == x && ((Position)arg0).y == y;
			}
			return false;
		}
		
		
		@Override
		public int hashCode() {
			int hash = 1;
			hash = hash * 17 + x;
			hash = hash * 31 + y;
			return hash;
		}
		
		
	}
	
	private directions retDir;
	public directions YFirstFinder(int fromX,int fromY, int toX,int toY){
		
		if(fromY != toY){
			
			if (fromY>toY) { 
				if(matrix[fromX][fromY-1] != gameElements.wall){
					return directions.up;
				}
			}
			else {
				if(matrix[fromX][fromY+1] != gameElements.wall){
					return directions.down;
				}
			}
			
		}
		if(fromX > toX){
			if(matrix[fromX-1][fromY] != gameElements.wall){
				return directions.left;
			}
		}
		return directions.right;
	}
	
	public directions YXirstFinder(int fromX,int fromY, int toX,int toY){
		if(fromX != toX){
			return (fromX > toX ) ? directions.left : directions.right;
		}
		else{
			return (fromY>toY) ? directions.up : directions.down;
		}
	}
	
	
	private ArrayList<Position> closedset, openset;
	private HashMap<Position,Position> came_from;
	private HashMap<Position, Double>g_score, f_score;
	public ArrayList<int[]> AStar(int startX, int startY, int endX, int endY){
		//AStar implementation thanks to wikipedia :)
		Position start = new Position(startX, startY);
		Position goal = new Position(endX,endY);
		closedset = new ArrayList<Position>();
		openset = new ArrayList<Position>();
		came_from = new HashMap<Position, Position>();
		g_score = new HashMap<Position, Double>();
		f_score = new HashMap<Pathfinder.Position, Double>();
		double tentative_g_score = 0;
		openset.add(start);
		g_score.put(start, 0.);
		f_score.put(start, g_score.get(start) + heuristic_cost_estimate(start,goal));
		
		while(! openset.isEmpty()){
			Position current = lowest_f_score_node();
			if (current.equals(goal)){
				return reconstruct_path(came_from,goal);
			}
			openset.remove(current);
			closedset.add(current);
			for(Position neighbor : neighbors(current)){
				if( ! closedset.contains(neighbor)){
					tentative_g_score = g_score.get(current) + dist_between(current,neighbor);
					if (! openset.contains(neighbor)  || g_score.get(neighbor) == null || tentative_g_score < g_score.get(neighbor)){
						came_from.put(neighbor,current);
						g_score.put(neighbor, tentative_g_score);
						f_score.put(neighbor, g_score.get(neighbor) +  heuristic_cost_estimate(neighbor, goal));
						if ( ! openset.contains(neighbor)){
							openset.add(neighbor);
						}
					}
				}
			}
			
			
		}
		
		return null;
	}
	

	private Position lowest_f_score_node(){
		double min = Double.MAX_VALUE;
		Position ret = null;
		for(Position p : openset){
			if(f_score.get(p) < min){
				ret = p;
				min = f_score.get(p);
			}
		}
		return ret;
	}
	
	private ArrayList<int[]> reconstruct_path(HashMap<Position, Position> from,Position to){
		total_path.clear();
		total_path.add(to.toIntArray());
		while(came_from.containsKey(to)){
			to = came_from.get(to);
			total_path.add(to.toIntArray());
		}
		return total_path;
	}
	
	private double heuristic_cost_estimate(Position start, Position goal){
		//Manhattan heuristic:
//		return Math.abs(goal.x - start.x) + Math.abs(goal.x - start.y);
		//
		return Math.sqrt(Math.pow(goal.x - start.x, 2) + Math.pow(goal.y - start.y,2));
	}
	
	private int tmpX,tmpY;
	private GameObject currentPos;
	public ArrayList<Position> neighbors(Position p){
		
		
		neighborsTmp.clear();
		
		if(p.x < GameWorld.map.width && p.y < GameWorld.map.width && p.x >= 0 && p.y >= 0){
			for(directions d: directions.values()){
				tmpX = p.x;
				tmpY = p.y;
				currentPos = GameWorld.map.getElement(p.x, p.y);
				if(currentPos instanceof Wormhole){
					tmpX = ( (Wormhole) currentPos).linked.x;
					tmpY = ( (Wormhole) currentPos).linked.y;
				}
				switch(d){
				case left:
					if(tmpX > 0){
						tmpX--;
					}
					else{
						continue;
					}
					break;
				case right:
					if(tmpX < GameWorld.map.width-1){
						tmpX++;
					}
					else{
						continue;
					}
					break;
				case up:
					if(tmpY > 0){
						tmpY--;
					}
					else{
						continue;
					}
					break;
				case down:
					if(tmpY < GameWorld.map.height-1){
						tmpY++;
					}
					else{
						continue;
					}
					break;
				}
				
				if ( (tmpX != p.x || tmpY != p.y) && ( matrix[tmpX][tmpY] != gameElements.wall) ){
					neighborsTmp.add(new Position(tmpX, tmpY));
				}
			}
		}
		return neighborsTmp;
	}
	
	private int dist_between(Position current, Position neighbor){
		return 1;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
