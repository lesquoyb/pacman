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
	protected Body body;
	public static String name;
	
	protected GameObject(byte x, byte y, Texture texture){
		this.texture = texture;
		bodyDef = new BodyDef();
		bodyDef.position.x = x ;
		bodyDef.position.y = y ;
		fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getWidth()/2, texture.getHeight()/2);
		fixtureDef.shape = shape;
	}
	
	public abstract void update();
	
	public void render(SpriteBatch batch){
		batch.draw(texture, body.getPosition().x - texture.getWidth()/2 , body.getPosition().y -texture.getWidth()/2 );
	}
	

}
