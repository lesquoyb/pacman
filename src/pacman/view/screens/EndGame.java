package pacman.view.screens;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndGame implements Screen{

	private boolean victory;
	private Texture texture;
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
			texture = ResourceManager.getTexture(ResourceManager.victoire);
		}
		else{
			texture = ResourceManager.getTexture(ResourceManager.defaite);
		}
		batch = new SpriteBatch();
		
	}
	
	
	public void qContinue(){
		texture = ResourceManager.getTexture(ResourceManager.qContinue);
	}

	@Override
	public void render(float delta) {
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
