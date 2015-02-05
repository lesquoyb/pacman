package pacman.controller;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.PacmanGame;
import pacman.model.gameobjects.Pacman;
import pacman.view.screens.EndGame;

import com.badlogic.gdx.Screen;

public class GameController extends ScreenController {

	private Pacman p ;
	
	
	public GameController(PacmanGame g, Screen v) {
		super(g, v);
	}

	@Override
	public void update() {
		p = GameWorld.getPacman();
		if(p != null){
			if(p.eatedGum == GameWorld.map.nbGum){
				game.endGame(true, "score: " + p.score);
			}
			else if( ! p.isAlive() ){
				game.endGame(false,"score: " + p.score);
			}
		}

	}

}
