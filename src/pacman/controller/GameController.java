package pacman.controller;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.gamelogic.PacmanGame;
import pacman.controller.resources.ResourceManager;
import pacman.model.gameobjects.Pacman;
import pacman.view.screens.GameScreen;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameController extends ScreenController {

	private Pacman p ;
	private GameWorld world;
	
	
	public GameController(PacmanGame g, GameScreen v) {
		super(g, v);
		world = ((GameScreen)v).world;
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
			if(world.totalGumEated == GameWorld.map.nbGum){
				game.endGame(true, "score: " + world.score);
			}
			else if( ! p.isAlive() ){
				ResourceManager.getSound(ResourceManager.pacmanDeath).play();
				world.remainingLife--;
				if(world.remainingLife == 0 ){
					game.endGame(false,"score: " + world.score + "pacgum ramassée(s): " + Integer.toString(world.totalGumEated) + "/"  + Integer.toString(GameWorld.map.nbGum));
				}
				else{
					
					((GameScreen)view).world.newLife();
				}
			}
		}

	}

}
