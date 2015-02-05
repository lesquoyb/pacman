package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;

import com.badlogic.gdx.Screen;

public class EndGameController extends ScreenController {

	
	private float begin;
	private final int TOTAL = 100;
	
	public EndGameController(PacmanGame g, Screen v) {
		super(g, v);
		begin = System.currentTimeMillis();
	}

	@Override
	public void update() {
		if( ( System.currentTimeMillis() - begin ) > TOTAL ){
			game.goToMainMenu();
		}
	}

}
