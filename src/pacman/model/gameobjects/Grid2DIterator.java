package pacman.model.gameobjects;

import java.util.Iterator;


public class Grid2DIterator<E> implements Iterator<E>{

	
	//TODO: faire générique et implémenter dans grid2D
	private int currentX, currentY;
	private Grid2D<E> grid;
	
	public Grid2DIterator(Grid2D<E> grid) {
		currentX = 0;
		currentY = 0;
		this.grid = grid;
	}
	
	
	@Override
	public boolean hasNext() {
		return grid.getGrid().size() > currentX+1 &&   grid.getGrid().get(currentX+1).size() > currentY+1 ;
	}
	
	private void update(){
		currentX++;
		if(currentX == grid.getX()){
			currentY++;
			currentX = 0;
		}
	}

	@Override
	public E next() {
		if(hasNext()){
			update();
			return  grid.getGrid().get(currentX).get(currentY); 
		}
		return null;
	}
	
}

