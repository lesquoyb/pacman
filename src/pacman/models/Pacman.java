package pacman.models;

import pacman.PacmanGame;

import com.badlogic.gdx.graphics.Texture;

public class Pacman extends Character{

	public static final Texture pacmanTexture = new Texture("images/pacman.png");
	
	
	public Pacman(byte x, byte y) {
		super(x, y, pacmanTexture);
	}

	@Override
	protected void move(directions d) {
		if (PacmanGame.isDownPressed()){
			posX -= 10f;
		}
	}

	@Override
	public void update(){
		if (PacmanGame.isDownPressed()){
			move(directions.down);
		}
	}
	

	
}
