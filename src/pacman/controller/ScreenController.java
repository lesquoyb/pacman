package pacman.controller;

import pacman.controller.gamelogic.PacmanGame;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.Screen;

public abstract class ScreenController {
	
	
	protected PacmanGame game;
	protected Screen view;
	
	public ScreenController(PacmanGame g, Screen v){
		game = g;
		view = v;
		game.setScreen(view);
	}
	
	/*
	 * Change the screen to the main menu and dispose the previous screen
	 */
	public void goToMainMenu(){
		view.dispose();
		ResourceManager.dispose();
		game.goToMainMenu();
	}
	
	
	/*
	 * This method should be used to check if we need to change the screen or not
	 */
	abstract public void update();

}
