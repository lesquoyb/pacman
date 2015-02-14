package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class Floor extends StaticObject{

	
	private boolean pacgum;
	
	public Floor(int x, int y,int width, int height, boolean hasGum) {
		super(x, y,width, height, ResourceManager.rawFloor);
		setGum(hasGum);
	}
	
	
	public boolean hasGum(){return pacgum;}
	
	public void setGum(boolean hasGum){
		pacgum = hasGum;
		if (hasGum){
			animation = ResourceManager.floorGum;
		}
		else{
			animation = ResourceManager.rawFloor;
		}
		
	}
	
}
