package pacman.gameobjects;

import pacman.gamelogic.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class GameObject {
	
	protected Texture texture;
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	protected float textX;
	protected float textY;
	protected byte x;
	protected byte y;
	protected Body body;
	public static String name;
	
	protected GameObject(byte x, byte y, Texture texture,short category, short mask){
		this.texture = texture;
		this.x = x;
		this.y = y;
		bodyDef = new BodyDef();
		bodyDef.position.x = x * Map.tileWidth ;
		bodyDef.position.y = - y * Map.tileHeight;
		fixtureDef = new FixtureDef();
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		//body.setUserData(this);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getWidth()/2, texture.getHeight()/2);
		fixtureDef.shape = shape;
		textX = x * Map.tileWidth;
		textY = y * Map.tileHeight;
	}
	
	public byte getX(){
		return x;
	}
	
	public byte getY(){
		return y;
	}
	
	protected void updatePos(){
		textX = body.getPosition().x - texture.getWidth()/2;
		textY = body.getPosition().y - texture.getHeight()/2;
		x = (byte) ((byte) textX / Map.tileWidth);
		y = (byte) ((byte) textY / Map.tileHeight);
	}
	
	public abstract void update();
	
	protected static BitmapFont bmFont  = new BitmapFont(); //utilisé pour le debug
	public void render(SpriteBatch batch){
		batch.draw(texture, textX , textY );
		debugRender(batch);
	}
	
	
	private void debugRender(SpriteBatch batch){
		bmFont.setColor(Color.WHITE);
		bmFont.draw(batch, ".", textX,textY);
	}
	
	
	public void dispose(){
		texture.dispose();
	}
	

}
