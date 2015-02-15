package pacman.view.screens;

import pacman.controller.gamelogic.GameWorld;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	
	public GameWorld world;
	private HUD hud;

	
	@Override
	public void show() {
		world = new GameWorld();
		hud = new HUD(world);
	}

	@Override
	public void render(float delta) {
		world.update(delta);
		world.render();
		hud.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		world.resize(width,height);	
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		if(world != null){
			world.dispose();
		}
	}

}
