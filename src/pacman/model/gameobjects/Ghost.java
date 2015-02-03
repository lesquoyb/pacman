package pacman.model.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ghost extends Character{


	public Ghost(int x, int y, Texture tex) {
		super(x, y, tex);
	}

	@Override
	public abstract void update(float delta);

}
