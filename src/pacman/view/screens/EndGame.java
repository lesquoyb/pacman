package pacman.view.screens;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EndGame implements Screen{

	private boolean victory;
	private TextureRegion texture;
	private String score;
	private SpriteBatch batch;
	
	
	public EndGame(boolean victory, String score){
		this.victory = victory;
		this.score = score;
		GameWorld.menuFont.setColor(Color.BLACK);
	}
	
	@Override
	public void show() {
		if(victory){
			texture = ResourceManager.getTexture(ResourceManager.victoire,false);
		}
		else{
			texture = ResourceManager.getTexture(ResourceManager.defaite,false);
		}
		batch = new SpriteBatch();
		
	}
	
	
	public void qContinue(){
		texture = ResourceManager.getTexture(ResourceManager.qContinue,false);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			batch.draw(texture, 0, 0);
			ResourceManager.getFont(ResourceManager.menuFont).draw(batch, score, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
