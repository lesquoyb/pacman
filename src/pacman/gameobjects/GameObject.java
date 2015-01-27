package pacman.gameobjects;

import com.badlogic.gdx.graphics.Texture;
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
		bodyDef.position.x = x *32f ;
		bodyDef.position.y = - y * 32f;
		fixtureDef = new FixtureDef();
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getWidth()/2, texture.getHeight()/2);
		fixtureDef.shape = shape;
	}
	
	public byte getX(){
		return x;
	}
	
	public byte getY(){
		return y;
	}
	
	protected void updateTextPos(){
		textX = body.getPosition().x - texture.getWidth()/2;
		textY = body.getPosition().y - texture.getHeight()/2;
	}
	
	public abstract void update();
	
	public void render(SpriteBatch batch){
		batch.draw(texture, textX , textY );
	}
	
	public void dispose(){
		texture.dispose();
	}
	

}
