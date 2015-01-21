package pacman.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class StaticObject extends GameObject{

	protected StaticObject(byte x, byte y, Texture texture) {
		super(x, y, texture);
		bodyDef.type = BodyType.StaticBody;
	}

	@Override
	public void update() { /* do nothing as it's immobile */	}

}
