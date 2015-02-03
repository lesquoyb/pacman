package pacman.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public abstract class StaticObject extends GameObject{

	protected StaticObject(int x, int y, Texture texture) {
		super(x, y, texture);
		updatePos();
	}

	@Override
	public void update(float delta) { 
		
	/* do nothing as it's a static object */	
	
	}

}
