package pacman.model.generators;

import java.util.ArrayList;
import java.util.HashMap;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.Map;
import pacman.controller.gamelogic.Map.gameElements;
import pacman.model.gameobjects.Floor;
import pacman.model.gameobjects.GameObject;
import pacman.model.gameobjects.Wall;
import pacman.model.gameobjects.MovingObject.directions;

public class Pathfinder {

	
	//TODO faire un singleton + destruction pour pouvoir utiliser sur plusieurs maps
	
	
	/*
	
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

	

	private static final int noMaxDepth = -1;
	protected ArrayList<int[]> findPacman(){
		return findPacman(noMaxDepth);
	}
	protected ArrayList<int[]>  tmp;
	protected ArrayList<int[]> findPacman(int profondeurMax){
		return findPacmanRecursif(null, profondeurMax, x, y);
	}
	
	
	private int[] posRet;
	private ArrayList<int[]> visited, best;
	private ArrayList<int[]> findPacmanRecursif(ArrayList<int[]>precedents, int profondeur, int tmpX, int tmpY){
		
		
		if(precedents == null){
			//premier passage: initialisation
			precedents = new ArrayList<int[]>();
			best = null;
			visited = new ArrayList<int[]>();
			tmp.clear();
		}
		else{
			precedents.add(new int[]{tmpX,tmpY});
		}
		
		
		if(GameWorld.getPacman().x == tmpX && GameWorld.getPacman().y == tmpY){
			return precedents;
		}
		if(profondeur == 0 ){
			return null;
		}
		profondeur--;
		
		if(! alreadyVisited(tmpX, tmpY)){
			visited.add(new int[]{tmpX,tmpY});
			for(directions d : directions.values()){
				if(canMove(d, tmpX, tmpY)){
					posRet = positionAfterMovement(d, tmpX, tmpY);
					tmp = findPacmanRecursif(precedents, profondeur, posRet[0], posRet[1]);
					if(tmp != null){
						if(best == null || best.size() > tmp.size()){
							best = new ArrayList<int[]>(tmp);
						}
					}
				}
			}
		}
		return best;
	}
	
	
	
	private boolean alreadyVisited(int tmpX, int tmpY){
		for (int[] tuple : visited){
			if(tuple[0] == tmpX && tuple[1] == tmpY){
				return true;
			}
		}
		return false;
	}
	
	*/
	
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
//		public Position(int[] a){
//			new Position(a[0],a[1]);
//		}
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
	private ArrayList<Position> closedset, openset;
	private HashMap<Position,Position> came_from;
	private HashMap<Position, Integer>g_score, f_score;
	public ArrayList<int[]> AStar(int startX, int startY, int endX, int endY){
		//AStar implementation thanks to wikipedia :)
		Position start = new Position(startX, startY);
		Position goal = new Position(endX,endY);
		closedset = new ArrayList<Position>();
		openset = new ArrayList<Position>();
		came_from = new HashMap<Position, Position>();
		g_score = new HashMap<Position, Integer>();
		f_score = new HashMap<Pathfinder.Position, Integer>();
		int tentative_g_score = 0;
		openset.add(start);
		g_score.put(start, 0);
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
		int min = Integer.MAX_VALUE;
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
	
	private int heuristic_cost_estimate(Position start, Position goal){
		//Manhattan heuristic:
		return Math.abs(goal.x - start.x) + Math.abs(goal.x - start.y);
	}
	
	private int tmpX,tmpY;
	public ArrayList<Position> neighbors(Position p){
		neighborsTmp.clear();
		//TODO: pb le noeud 21:8 n'est pas selectionné
		for(directions d: directions.values()){
			tmpX = p.x;
			tmpY = p.y;
			switch(d){
			case left:
				if(tmpX > 0)
				tmpX--;
				break;
			case right:
				if(tmpX < GameWorld.map.width){
					tmpX++;
				}
				break;
			case up:
				if(tmpY > 0){
					tmpY--;
				}
				break;
			case down:
				if(tmpY < GameWorld.map.height){
					tmpY++;
				}
				break;
			}
			if ( (tmpX != p.x || tmpY != p.y) && ( matrix[tmpX][tmpY] != gameElements.wall) ){
				neighborsTmp.add(new Position(tmpX, tmpY));
			}
		}
		return neighborsTmp;
	}
	
	private int dist_between(Position current, Position neighbor){
		return 1;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
