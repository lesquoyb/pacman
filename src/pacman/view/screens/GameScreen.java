package pacman.view.screens;

import pacman.gamelogic.GameWorld;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	
	private GameWorld world;

	
	
	@Override
	public void show() {
		world = new GameWorld();
	}

	@Override
	public void render(float delta) {
		world.update(delta);
		world.render();
		
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
