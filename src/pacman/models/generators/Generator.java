package pacman.models.generators;

import com.badlogic.gdx.files.FileHandle;

public abstract class Generator {
	
	protected FileHandle file;
	
	public Generator(FileHandle f){
		file = f;
	}
	
	/**
	 * @return true ou false pour la réussite de la génération
	 */
	public abstract boolean generate();
	
}
