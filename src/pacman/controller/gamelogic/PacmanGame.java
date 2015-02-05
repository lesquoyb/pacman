package pacman.controller.gamelogic;

import pacman.controller.EndGameController;
import pacman.controller.GameController;
import pacman.controller.MainMenuController;
import pacman.controller.ScreenController;
import pacman.controller.SplashController;
import pacman.controller.resources.ResourceManager;
import pacman.view.screens.EndGame;
import pacman.view.screens.GameScreen;
import pacman.view.screens.MainMenuScreen;
import pacman.view.screens.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

public class PacmanGame extends Game {
		
	ScreenController screenController;
	
	@Override
	public void create () {
		screenController = new SplashController(new SplashScreen(), this);
	}

	@Override
	public void render () {
		screenController.update();
		super.render();
	}
	
	public void goToMainMenu(){
		screenController = new MainMenuController(this,new MainMenuScreen());
	}
	
	
	public void startNewGame(){
		screenController = new GameController(this,new GameScreen());
	}
	
	public void endGame(boolean victory, String score){
		screenController = new EndGameController(this,new EndGame(victory,score));
	}

	
	@Override
	public void dispose(){
		super.dispose();
		ResourceManager.dispose();
	}
	
	@Override
	public void setScreen(Screen screen) {
		if(this.screen != null){
			this.screen.dispose();			
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
