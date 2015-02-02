package pacman.gameobjects;

import pacman.gamelogic.GameWorld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends MovingObject {

	protected Vector2 movement;
	protected directions direction;

	public Character(int x, int y, Texture texture) {
		super(x, y, texture);
		movement = new Vector2();
		direction = null;

	}

	
	
	@Override
	public void update(float delta){
		
		if(direction != null){		
			left += movement.x * delta;
			top += movement.y * delta;
			updatePos();
			float to_testX = left, to_testY = bottom;
			switch(direction){
				case left:
					to_testY = center.y;
					break;
				case right:
					to_testX = right;
					to_testY = center.y;
					break;
					
				case up:
					to_testX = center.x;
					to_testY = top;
					break;
					
				case down:
					to_testX = center.x;
					break;
			}

			Wall wall  = GameWorld.map.getWall(to_testX,to_testY);
			if ( wall != null ){
				switch (direction){
				
					case left:
						left = wall.right;
						break;
						
					case right:
						left = wall.left - width;
						break;
						
					case up:
						top = wall.bottom ;
						break;
						
					case down:
						top = wall.top - height;
						break;
				}
				updatePos();
			}
		}
	
	}
}
