package pacman.model.gameobjects;


public abstract class MovingObject extends GameObject{

	enum directions {left,right,up,down};
	
	protected MovingObject(int x, int y, int width, int height, String anim) {
		super(x, y, width, height,anim);
	}


	@Override
	public abstract void update(float delta);

	

	
}
