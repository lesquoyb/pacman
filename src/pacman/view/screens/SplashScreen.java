package pacman.view.screens;

import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen{

	private SpriteBatch batch ;
	private Sprite splash ;
	private boolean end ;
	private int count;
	private final int MAX = 1;
	private final float speed = 0.75f;
	private boolean back;
	
	@Override
	public void show() {
		batch  = new SpriteBatch();
		splash = new Sprite(ResourceManager.getTexture(ResourceManager.splash) );

		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		splash.setAlpha(1);
		end = false;
		back = false;
		count = 1;
	}

	public boolean isEnded(){return end;	}
	
	public void update(float delta){
		if(! end){
			if(!back){
				float newA = splash.getColor().a  - speed * delta;
				if(newA > 0 ) splash.setAlpha(newA);
				else back =true;
			}
			else{
				float newA = splash.getColor().a + speed * delta;
				if(newA < 1 )
					splash.setAlpha(newA);
				else{
					if(count == MAX){
						end = true;
					}
					else{
						count ++;
						splash.setAlpha(1);
						back = false;
					}
				}
			}
		}
	}
	
	@Override
	public void render(float delta) {
		
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		splash.setSize(width, height);
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
		batch.dispose();
	}

}
