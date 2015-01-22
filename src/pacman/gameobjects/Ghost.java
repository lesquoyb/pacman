package pacman.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public class Ghost extends Character{

	public static final Texture ghostTexture = new Texture("images/ghost.png");
	
	public Ghost(byte x, byte y) {
		super(x, y, ghostTexture);
	}

	@Override
	public void update() {
		// TODO IA des fantomes
		
	}


}
