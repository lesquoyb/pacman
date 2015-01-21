package pacman;

import pacman.models.Map;
import pacman.models.Pacman;
import pacman.view.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PacmanGame extends Game {
		
	
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	
	public static boolean isLeftPressed(){
		return Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.Q);
	}
	public static boolean isUpPressed(){
		return Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.Z);
	}
	public static boolean isDownPressed(){
		return Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);
	}	
	public static boolean isRightPressed(){
		return Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
	}

}
