package pacman.model.gameobjects;

import pacman.controller.gamelogic.Map;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {


	public float left, right, top, bottom;
	public int width, height;
	public int x;
	public int y;
	protected Vector2 center;
	protected String animation;
	
	
	protected GameObject(int x, int y,int width,int height, String anim){
		this.x = x;
		this.y = y;
		animation = anim;
		left = x * Map.tileWidth ;
		top =  y * Map.tileHeight ;
		this.width = width;
		this.height = height;
		center = new Vector2(left + width/2, top + height/2);
	}
	
	public String getAnimation(){return animation;}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	protected void updatePos(){
		x = (int)( left / Map.tileWidth);
		y =  (int)( top / Map.tileHeight);
		center.x = left + width/2;
		center.y = top + height/2;
		right = left + width;
		bottom = top + height;
	}
	
	public abstract void update(float delta);
	

}
