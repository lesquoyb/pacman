package pacman.model.gameobjects;


public abstract class StaticObject extends GameObject{

	protected StaticObject(int x, int y, int width, int height, String anim) {
		super(x, y, width, height,anim);
		updatePos();
	}

	@Override
	public void update(float delta) { 
		
	/* do nothing as it's a static object */	
	
	}

}
