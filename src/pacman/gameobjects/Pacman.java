package pacman.gameobjects;

import graphics.TextureFactory;
import pacman.PacmanGame;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends Character{

	//public static final Texture pacmanTexture = new Texture("images/pacman.png");
	public static final String name = "pacman";
	
	public Pacman(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
	}

	protected void move(directions d) {
		switch(d){
		case down:
			body.setLinearVelocity(new Vector2(0,-10));
			break;
		case up:
			body.setLinearVelocity(new Vector2(0,10));
			break;
		case left:
			body.setLinearVelocity(new Vector2(-10,0));
			break;
			
		case right:
			body.setLinearVelocity(new Vector2(10,0));
			break;
		}
	}

	@Override
	public void update(){
		
		if (PacmanGame.isDownPressed()){
			move(directions.down);
		}
		if (PacmanGame.isLeftPressed()){
			move(directions.left);
		}
		if (PacmanGame.isRightPressed()){
			move(directions.right);
		}
		if (PacmanGame.isUpPressed()){
			move(directions.up);
		}
		
		
		updateTextPos();
	}
	

	
}
