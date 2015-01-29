package pacman.gameobjects;

import pacman.gamelogic.GameWorld;
import pacman.gamelogic.Map;
import pacman.graphics.B2DVars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends MovingObject {

	public Character(byte x, byte y, Texture texture) {
		super(x, y, texture,B2DVars.playerLayer,(short)~B2DVars.wallLayer);
		
		
	}

	
	
	private static 	Vector2 bottomLeft = new Vector2() , bottomRight = new Vector2() , topLeft  = new Vector2(),topRight = new Vector2();
	@Override
	public void update(){
		updatePos();
		
		bottomLeft.x = textX;
		bottomLeft.y = textY;
		if(GameWorld.map.isWall(bottomLeft)){
			System.out.println("collision en bas à gauche");
		}
		
		bottomRight.x = textX + Map.tileWidth;
		bottomRight.y = textY;
		if(GameWorld.map.isWall(bottomRight)){
			System.out.println("collision en bas à droite");
			
		}
		
		topLeft.x = textX;
		topLeft.y = textY + Map.tileHeight;
		if(GameWorld.map.isWall(topLeft)){
			System.out.println("collision en haut à gauche");
			
		}
		
		topRight.x = textX + Map.tileWidth;
		topRight.y = textY + Map.tileHeight;
		if(GameWorld.map.isWall(topRight)){
			System.out.println("collision en haut à droite");
			
		}
	}
}
