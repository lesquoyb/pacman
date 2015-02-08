package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;
import pacman.view.screens.EndGame;

public class EndGameController extends ScreenController {

	
	private long begin;
	private final long TOTAL = 2500000;
	private boolean first = true;
	
	public EndGameController(PacmanGame g, EndGame v) {
		super(g, v);
		begin = System.nanoTime();
	}

	@Override
	public void update() {
		if( (  ( System.nanoTime() - begin ) > TOTAL ) && first ){
			( (EndGame) view ).qContinue();
			first = false;
		}
		else if ( ! first){
			if(PacmanGame.isStartPressed()){
				game.goToMainMenu();
			}
		}
	}

}
