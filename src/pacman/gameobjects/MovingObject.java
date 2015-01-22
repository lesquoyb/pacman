package pacman.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class MovingObject extends GameObject{

	enum directions {left,right,up,down};
	
	protected MovingObject(byte x, byte y, Texture texture) {
		super(x, y, texture);
		bodyDef.type = BodyType.DynamicBody;
		body = GameWorld.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}


	@Override
	public abstract void update();

	

	
}
