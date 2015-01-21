package pacman.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class GameObject {
	
	protected byte posX;
	protected byte posY;
	protected Texture texture;
	protected BodyDef bodyDef;
	protected FixtureDef fixtureDef;
	
	protected GameObject(byte x, byte y, Texture texture){
		posX = x;
		posY = y;
		this.texture = texture;
		bodyDef = new BodyDef();
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getWidth(), texture.getHeight());
		fixtureDef.shape = shape;
		
	}
	
	public abstract void update();
	
	public void render(SpriteBatch batch){
		batch.draw(texture, posX, posY);
	}
	

}
