package pacman.controller;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.PacmanGame;
import pacman.model.gameobjects.Pacman;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameController extends ScreenController {

	private Pacman p ;
	
	
	public GameController(PacmanGame g, Screen v) {
		super(g, v);
		Timer t = new Timer();
		t.scheduleTask(new Task() {
			
			@Override
			public void run() {
				GameWorld.secondsToEnd--;
			}
		}, 0, 1);
	}

	@Override
	public void update() {
		p = GameWorld.getPacman();
		if(p != null){
			if(p.eatedGum == GameWorld.map.nbGum){
//				view.dispose();
				game.endGame(true, "score: " + GameWorld.score);
			}
			else if( ! p.isAlive() ){
//				view.dispose();
				game.endGame(false,"score: " + GameWorld.score + "pacgum ramassée(s): " + Integer.toString(p.eatedGum) + "/"  + Integer.toString(GameWorld.map.nbGum));
			}
		}

	}

}
