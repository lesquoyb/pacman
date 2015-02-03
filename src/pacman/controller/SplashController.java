package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;
import pacman.view.screens.SplashScreen;

public class SplashController extends ScreenController {
	
	
	public SplashController(SplashScreen v, PacmanGame g){
		super(g, v);
	}
	
	
	
	@Override
	public void update(){
		if(((SplashScreen)view).isEnded()){
			game.goToMainMenu();
		}
	}

}
