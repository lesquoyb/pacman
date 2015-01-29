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

	
	
	private static 	Vector2 bottomLeft = new Vector2() , bottomRight = new Vector2() , topLeft  = new Vector2(),topRight = new Vector2(), replace = new Vector2();
	@Override
	public void update(){
		updatePos();
		System.out.println(textX + " " + textY +" " + x + " " + y);
		bottomLeft.x = textX;
		bottomLeft.y = textY;
		//tout faux
		if(GameWorld.map.isWall(bottomLeft)){
			System.out.println("collision en bas à gauche");
			replace.x = (((int)(textX / Map.tileWidth)) + 1) * Map.tileWidth;
			replace.y = textY;
			body.setTransform(replace, 0);
			updatePos();
		}
		
		bottomRight.x = textX + Map.tileWidth;
		bottomRight.y = textY;
		if(GameWorld.map.isWall(bottomRight)){
			System.out.println("collision en bas à droite");
			replace.x = (((int)(textX / Map.tileWidth)) - 1) * Map.tileWidth;
			replace.y = textY;
			body.setTransform(replace, 0);
			updatePos();
		}
		
		topLeft.x = textX;
		topLeft.y = textY + Map.tileHeight;
		if(GameWorld.map.isWall(topLeft)){
			System.out.println("collision en haut à gauche");
			replace.x = textX;
			replace.y = ( (int) (textY / Map.tileWidth) - 1) * Map.tileHeight ;
			body.setTransform(replace, 0);
			updatePos();
			
		}
		
		topRight.x = textX + Map.tileWidth;
		topRight.y = textY + Map.tileHeight;
		if(GameWorld.map.isWall(topRight)){
			System.out.println("collision en haut à droite");
			replace.x = (((int)(textX / Map.tileWidth)) + 1) * Map.tileWidth;
			replace.y = ( (int) (textY / Map.tileWidth) + 1) * Map.tileHeight;
			body.setTransform(replace, 0);
			updatePos();
		}
	}
}
