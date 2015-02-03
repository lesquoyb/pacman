package pacman.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public abstract class MovingObject extends GameObject{

	enum directions {left,right,up,down};
	
	protected MovingObject(int x, int y, Texture texture) {
		super(x, y, texture);
	}


	@Override
	public abstract void update(float delta);

	

	
}
