package pacman.view.screens;

import pacman.controller.gamelogic.GameWorld;
import pacman.controller.resources.ResourceManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUD {

	private SpriteBatch batch;
	private GameWorld world;
	private final BitmapFont scoreFont = ResourceManager.getFont(ResourceManager.scoreFont);
	private final TextureRegion heart = ResourceManager.getTexture(ResourceManager.heart,false);

	
	public HUD(GameWorld w){
		world = w;
		batch = new SpriteBatch();
		scoreFont.setColor(Color.RED);
		scoreFont.setScale(1.75f);
	}
	
	
	public void draw(){
		batch.begin();
			scoreFont.draw(batch, "score: " + world.score , 0, Gdx.graphics.getHeight());
			scoreFont.draw(batch,  toTime(world.secondsToEnd),Gdx.graphics.getWidth() -100, Gdx.graphics.getHeight());
			scoreFont.draw(batch,Integer.toString(Gdx.graphics.getFramesPerSecond()),0,50);
			for(int i = 0 ; i < world.remainingLife ; i++){
				batch.draw(heart, Gdx.graphics.getWidth() - (world.remainingLife - i)* heart.getRegionWidth() -30 ,0); 
			}
			if(! world.begin){
				printChrono();
			}
			
		batch.end();
	}
	
	private void printChrono(){
		scoreFont.draw(batch, Byte.toString(world.chrono), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
	
	private String toTime(int seconds){
		int minutes = 0, tmp = seconds;
		while( (tmp / 60) >= 1){
			minutes++;
			tmp-= 60;
		}
		return Integer.toString(minutes) + ":" + Integer.toString(seconds%60);
	}
	
}
