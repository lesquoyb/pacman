package pacman.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class MovingObject extends GameObject{

	enum directions {left,right,up,down};
	
	protected MovingObject(byte x, byte y, Texture texture) {
		super(x, y, texture);
		bodyDef.type = BodyType.KinematicBody;//we use kinematicBody as we don't want to use any physics at all for the moment
	}


	@Override
	public abstract void update();
		
	protected boolean canMove(directions d){
		//TODO
		return false;
	}
	
	protected void move(directions d){
		if ( canMove(d) ) {
			switch(d){
				case up:
					posY++ ;
					break;
					
				case down:
					posY-- ;
					break;
					
				case right:
					posX++ ;
					break;
					
				case left:
					posX-- ;
					break;
			}
		}
	}
	
}
