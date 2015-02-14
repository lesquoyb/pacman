package pacman.model.gameobjects;

public class StartingPoint extends Floor {

	public enum characters {pacman, Bghost, Rghost, Yghost, Gghost};
	
	private characters character;
	
	public StartingPoint(int x, int y, int width, int height, characters character) {
		super(x, y,width, height,false);
		this.character = character;
	}

	
	public characters getCharacter(){
		return character;
	}
}
