package pacman.gameobjects;

import pacman.graphics.B2DVars;

import com.badlogic.gdx.graphics.Texture;

public abstract class Character extends MovingObject {

	public Character(byte x, byte y, Texture texture) {
		super(x, y, texture,B2DVars.playerLayer,B2DVars.maskAll);
		
	}

	
	@Override
	public abstract void update() ;
}
