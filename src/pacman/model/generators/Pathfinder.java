package pacman.model.generators;

import java.util.ArrayList;

import pacman.controller.gamelogic.Map;
import pacman.controller.gamelogic.Map.gameElements;
import pacman.model.gameobjects.Floor;
import pacman.model.gameobjects.GameObject;
import pacman.model.gameobjects.Wall;

public class Pathfinder {

	
	//TODO faire un singleton + destruction pour pouvoir utiliser sur plusieurs maps
	
	
	private Map.gameElements[][] matrix;
	
	public Pathfinder(ArrayList<ArrayList<GameObject>> grid){
		transformToMatrix(grid);
	}
	// {floor,wall,pacman,superpacgum,Bghost,Rghost,Yghost,Gghost,wormhole}
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
				x++;
			}
			x = 0;
			y++;
		}
	}
	
	
	public ArrayList<int[]> findPath(int fromX, int fromY, int toX, int toY){
		ArrayList<int[]> ret = new ArrayList<int[]>();
		
		return ret;
	}
	
}
