package pacman.model.gameobjects;


public abstract class Ghost extends Character{


	public Ghost(int x, int y,int width, int height, String anim) {
		super(x, y, width, height,anim);
	}

	@Override
	public abstract void update(float delta);

}
