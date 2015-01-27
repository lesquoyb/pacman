package pacman.gameobjects;

import pacman.gamelogic.GameWorld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class MovingObject extends GameObject{

	enum directions {left,right,up,down};
	
	protected MovingObject(byte x, byte y, Texture texture,short category, short mask) {
		super(x, y, texture,category, mask);
		bodyDef.type = BodyType.DynamicBody;
		body = GameWorld.world.createBody(bodyDef);
		body.createFixture(fixtureDef);
	}


	@Override
	public abstract void update();

	

	
}
