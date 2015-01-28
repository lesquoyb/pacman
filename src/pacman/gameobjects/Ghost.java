package pacman.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public abstract class Ghost extends Character{

	//public static final Texture ghostTexture = new Texture("images/ghost.png");

	public Ghost(byte x, byte y, Texture tex) {
		super(x, y, tex);
	}

	@Override
	public abstract void update();

}
