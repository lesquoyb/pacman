package pacman.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public abstract class Character extends MovingObject {

	public Character(byte x, byte y, Texture texture) {
		super(x, y, texture);
	}

	
	@Override
	public abstract void update() ;
}
