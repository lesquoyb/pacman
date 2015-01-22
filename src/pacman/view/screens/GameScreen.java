package pacman.view.screens;

import pacman.gameobjects.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {

	
	private GameWorld world;

	
	
	@Override
	public void show() {
		world = new GameWorld();
	}

	@Override
	public void render(float delta) {
		world.update();
		world.render();
		
	}

	@Override
	public void resize(int width, int height) {
		world.resize(width,height);	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
