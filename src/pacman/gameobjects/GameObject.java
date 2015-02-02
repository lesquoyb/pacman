package pacman.gameobjects;

import pacman.gamelogic.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	
	protected Texture texture;
	public float left, right, top, bottom;
	public int width, height;
	public int x;
	public int y;
	protected Vector2 center;
	public static String name;
	
	protected GameObject(int x, int y, Texture texture){
		this.texture = texture;
		this.x = x;
		this.y = y;
		left = x * Map.tileWidth ;
		top =  y * Map.tileHeight ;
		width = texture.getWidth();
		height = texture.getHeight();
		center = new Vector2(left + width/2, top + height/2);
	}
	
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
	
	protected static BitmapFont bmFont  = new BitmapFont(); //utilisé pour le debug
	public void render(SpriteBatch batch){
		batch.draw(texture, left  , top);
		//debugRender(batch);
	}
	
	
	private void debugRender(SpriteBatch batch){
		bmFont.setColor(Color.BLACK);
		bmFont.draw(batch, ".", left,top);
	}
	
	
	public void dispose(){
		texture.dispose();
	}
	

}
