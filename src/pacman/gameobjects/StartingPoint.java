package pacman.gameobjects;

public class StartingPoint extends Floor {

	public enum characters {pacman, Bghost, Rghost, Yghost, Gghost};
	
	private characters character;
	
	public StartingPoint(byte x, byte y,characters character) {
		super(x, y);
		this.character = character;
	}

	
	public characters getCharacter(){
		return character;
	}
}
