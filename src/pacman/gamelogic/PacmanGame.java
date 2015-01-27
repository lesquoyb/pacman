package pacman.gamelogic;

import pacman.view.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

public class PacmanGame extends Game {
		
	
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	
	
	
	@Override
	public void setScreen(Screen screen) {
		if(screen != null){
			screen.dispose();			
		}
		super.setScreen(screen);
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
