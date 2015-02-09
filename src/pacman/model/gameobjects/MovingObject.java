package pacman.model.gameobjects;


public abstract class MovingObject extends GameObject{

	enum directions {left,right,up,down};
	
	protected MovingObject(int x, int y, int width, int height, String anim) {
		super(x, y, width, height,anim);
	}


	@Override
	public abstract void update(float delta);

	
	public boolean areOnTheSameAxis(directions d1, directions d2){
		return 		( d1 == directions.up && d2 == directions.down )
				||  ( d1 == directions.down && d2 == directions.up )
				||  ( d1 == directions.left && d2 == directions.right)
				||  ( d1 == directions.right && d2 == directions.left);
	}
	
}
