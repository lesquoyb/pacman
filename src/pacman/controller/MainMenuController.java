package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;
import pacman.view.screens.MainMenuScreen;

import com.badlogic.gdx.Gdx;

public class MainMenuController extends ScreenController  {

	
	
	public MainMenuController(PacmanGame g, MainMenuScreen m) {
		super(g,m);
		m.setController(this);

		
	}

	




	public void exit(){
		//view.dispose();
		Gdx.app.exit();	
	}

	
	public void Play(){
		//view.dispose();
		game.startNewGame();
	}


	@Override
	public void update() {
		
	}

	
}
