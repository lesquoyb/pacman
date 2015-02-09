package pacman.model.gameobjects;

import java.util.ArrayList;
import java.util.Iterator;



public class Grid2D<GameObject> implements Iterable<GameObject> {
//TODO faire générique + itérateur et implémenter dans map

	
	
	private ArrayList<ArrayList<GameObject>> grid;
	private int x, y, currentX, currentY;
	
	 public Grid2D(int x, int y) {
		this.x = x;
		this.y = y;	
		currentX = 0;
		currentY = 0;
	}
	 
	 
	 public void addElement(GameObject elem){
		 if(currentX+1 == x){
			 currentX = 0;
			 currentY++;
			 grid.add(new ArrayList<GameObject>());
		 }
		 else{
			 currentX++;
		 }
		 if(currentX < x && currentY < y){
			 grid.get(currentX).add(elem);
		 }
	 }
	
	@Override
	public Iterator<GameObject> iterator() {
		return new Grid2DIterator<GameObject>(this);
	}

	public ArrayList<ArrayList<GameObject>> getGrid() {
		return grid;
	}

	public void setGrid(ArrayList<ArrayList<GameObject>> grid) {
		this.grid = grid;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}

