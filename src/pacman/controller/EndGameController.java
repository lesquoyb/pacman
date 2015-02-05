package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;

import com.badlogic.gdx.Screen;

public class EndGameController extends ScreenController {

	
	private float begin;
	private final long TOTAL = 1500;
	
	public EndGameController(PacmanGame g, Screen v) {
		super(g, v);
		begin = System.nanoTime();
	}

	@Override
	public void update() {
		System.out.println(System.nanoTime() +" "+ begin);
		if( ( System.nanoTime() - begin ) > TOTAL ){
			view.dispose();
			
			game.goToMainMenu();
		}
	}

}
