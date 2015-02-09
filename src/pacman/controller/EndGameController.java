package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;
import pacman.view.screens.EndGame;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class EndGameController extends ScreenController {

	
	private final float TOTAL_TIME_IN_S = 2.5f;
	private Timer timer;
	
	public EndGameController(PacmanGame g, EndGame v) {
		super(g, v);
		timer = new Timer();
		timer.scheduleTask(new Task(){

			@Override
			public void run() {
				( (EndGame) view ).qContinue();
			}
			
		}, TOTAL_TIME_IN_S);
	}

	@Override
	public void update() {
		if(PacmanGame.isStartPressed()){
			game.goToMainMenu();
		}
	}

}
