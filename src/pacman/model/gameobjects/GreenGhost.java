package pacman.model.gameobjects;


public class GreenGhost extends Ghost {



	public GreenGhost(int x, int y,int width, int height) {
		super(x, y, width, height, "fantomeV");
	}

	@Override
	public void update(float delta) {
		seekPacman(delta);
	}
	
}
