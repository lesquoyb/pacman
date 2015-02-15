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
	protected boolean animated;
	
	
	protected GameObject(int x, int y,int width,int height, String anim){
		this.x = x;
		this.y = y;
		animation = anim;
		left = x * Map.tileWidth ;
		top =  y * Map.tileHeight ;
		this.width = width;
		this.height = height;
		center = new Vector2(left + width/2, top + height/2);
		animated = false;
	}
	
	public String getAnimation(){return animation;}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isAnimated(){return animated;}
	
	protected void updatePos(){
		center.x = left + width/2;
		center.y = top + height/2;
		x = (int)( center.x / Map.tileWidth);
		y =  (int)( center.y / Map.tileHeight);
		right = left + Map.tileWidth;
		bottom = top + Map.tileHeight;
	}
	
	public abstract void update(float delta);
	

}
