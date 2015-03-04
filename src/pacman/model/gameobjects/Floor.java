package pacman.model.gameobjects;

import pacman.controller.resources.ResourceManager;


public class Floor extends StaticObject{

	
	private boolean pacgum;
	private boolean superPacgum;
	
	public Floor(int x, int y,int width, int height, boolean hasGum,boolean superPacgum ){
		super(x, y,width, height, ResourceManager.rawFloor);
		setGum(hasGum);
		setSuperPacgum(superPacgum);
	}
	
	
	public boolean hasGum(){return pacgum;}
	public boolean hasSuper(){return superPacgum;}
	
	public void setGum(boolean hasGum){
		pacgum = hasGum;
		if (hasGum){
			animation = ResourceManager.floorGum;
		}
		else if(! hasSuper()){
			animation = ResourceManager.rawFloor;
		}
		
	}
	
	public void setSuperPacgum(boolean superGum){
		superPacgum = superGum;
		if(superPacgum){
			animation = ResourceManager.floorSuper;
		}
		else if(! hasGum()){
			animation = ResourceManager.rawFloor;
		}
	}
	
}
