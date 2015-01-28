package pacman.gameobjects;

import pacman.gamelogic.GameWorld;
import pacman.gamelogic.PacmanGame;
import pacman.graphics.TextureFactory;

import com.badlogic.gdx.math.Vector2;

public class Pacman extends Character{

	//public static final Texture pacmanTexture = new Texture("images/pacman.png");
	public static final String name = "pacman";
	private Vector2 movement;
	private static final int speed = 10;
	
	public Pacman(byte x, byte y) {
		super(x, y, TextureFactory.getTexture(name));
		movement = new Vector2();
	}

	protected void move(directions d) {
		switch(d){
		case down:
			movement.x = 0;
			movement.y = - speed;
			body.setLinearVelocity(new Vector2(0,-10));
			break;
		case up:
			movement.x = 0;
			movement.y = speed;
			body.setLinearVelocity(new Vector2(0,10));
			break;
		case left:
			movement.x = - speed ;
			movement.y = 0;
			body.setLinearVelocity(new Vector2(-10,0));
			break;
			
		case right:
			movement.x = speed;
			movement.y = 0;
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

		/*
		
		if(! GameWorld.map.isObstacle(x,y)){

			System.out.println("avant: " +textX + " " + textY);
			textX += movement.x;
			textY += movement.y;
			x = (byte) (textX / GameWorld.map.tileWidth);
			y = (byte) (textY / GameWorld.map.tileHeight);
			System.out.println("après: " +textX + " " + textY);
		}
		*/
	}
	

	
}
