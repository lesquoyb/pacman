package pacman.controller.gamelogic;

import java.util.ArrayList;

import pacman.controller.resources.AnimationFactory;
import pacman.controller.resources.ResourceManager;
import pacman.model.gameobjects.BlueGhost;
import pacman.model.gameobjects.Character;
import pacman.model.gameobjects.GreenGhost;
import pacman.model.gameobjects.Pacman;
import pacman.model.gameobjects.RedGhost;
import pacman.model.gameobjects.StartingPoint;
import pacman.model.gameobjects.StartingPoint.characters;
import pacman.model.gameobjects.YellowGhost;
import pacman.model.generators.Pathfinder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FillViewport;


public class GameWorld {
	
	private SpriteBatch batch;
	private SpriteBatch batchInv;
	public static Map map;
	private OrthographicCamera camera;
	private FillViewport viewport;
	private ArrayList<Character> characters;
	private static Pacman pacman = null;
	private int score;
	private static final float fpsMin = 1/40f;
	public static int secondsToEnd;
	public static Pathfinder pathfinder;
	public final int TIMER_MAX = 300;
	public final int GUM_VALUE = 100;
	public final int TIME_VALUE = 10;
	public final static BitmapFont menuFont = ResourceManager.getFont(ResourceManager.menuFont);;
	
	
	public GameWorld(){
			
			menuFont.setColor(Color.WHITE);
			secondsToEnd = TIMER_MAX;
			characters = new ArrayList<Character>();
			camera = new OrthographicCamera();
			camera.setToOrtho(true); 
			viewport =  new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			viewport.setCamera(camera);
			viewport.apply();
			camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);


			batch = new SpriteBatch();
			batchInv = new SpriteBatch();
			try {
				map = new Map(  Gdx.files.internal("config/map1.map") );
			
			
			} catch (Exception e) {
				//TODO ouvrir une fênetre pour afficher l'erreur
				System.out.println("Une erreur est survenue lors de la création de la map: "+ e.getMessage());
				Gdx.app.exit();
			}
			pathfinder = new Pathfinder(map.grid);
			camera.update();
			for(StartingPoint startingPoint : map.getStartingPoints()){
				characters c = startingPoint.getCharacter();
				int x = startingPoint.getX();
				int y = startingPoint.getY();
				Character character = null;
				switch(c){
					case pacman:
						character = new Pacman(x, y, Map.tileWidth, Map.tileHeight);
						pacman = (Pacman) character;
						break;
					case Bghost :
						character = new BlueGhost(x,y, Map.tileWidth, Map.tileHeight);
						break;
					case Rghost :
						character = new RedGhost(x,y, Map.tileWidth, Map.tileHeight);
						break;
					case  Yghost:
						character = new YellowGhost(x,y, Map.tileWidth, Map.tileHeight);
						break;
					case Gghost :
						character = new GreenGhost(x,y, Map.tileWidth, Map.tileHeight);
						break;
				}
					characters.add(character);
			}


	}
	
	TextureRegion texture;
	public void render(){
		
		
		camera.update();
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			map.render(batch);
			for(Character c : characters){
				texture = ResourceManager.getTexture(c.getAnimation(),c.isAnimated());
				batch.draw(texture,c.left + (map.tileWidth - texture.getRegionWidth())/2 ,c.top);
			}
		batch.end();
		
		batchInv.begin();
			//batchInv.draw(ResourceManager.getTexture(ResourceManager.fondScore),0, Gdx.graphics.getHeight()- ResourceManager.getTexture(ResourceManager.fondScore).getHeight());
			menuFont.draw(batchInv, score + "/" + map.nbGum, 0, Gdx.graphics.getHeight());
			//batchInv.draw(ResourceManager.getTexture(ResourceManager.fondScore),Gdx.graphics.getWidth() - ResourceManager.getTexture(ResourceManager.fondScore).getWidth(), Gdx.graphics.getHeight()- ResourceManager.getTexture(ResourceManager.fondScore).getHeight());
			menuFont.draw(batchInv,  toTime(secondsToEnd),Gdx.graphics.getWidth() -100, Gdx.graphics.getHeight());
			menuFont.draw(batchInv,Integer.toString(Gdx.graphics.getFramesPerSecond()),0,50);
		batchInv.end();
	}
	
	private String toTime(int seconds){
		int minutes = 0, tmp = seconds;
		while( (tmp / 60) >= 1){
			minutes++;
			tmp-= 60;
		}
		return Integer.toString(minutes) + ":" + Integer.toString(seconds%60);
	}
	
	public void resize(int width,int height){
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);

	}
	
	private float dt;
	public void update(float delta){
		AnimationFactory.update(delta);
		dt = Math.min(delta,fpsMin);
		map.update(dt);
		for(Character c : characters){
			c.update(dt);
			if(c instanceof Pacman){
				score = ((Pacman) c).eatedGum * GUM_VALUE + ( secondsToEnd - TIMER_MAX)* TIME_VALUE ;
			}
		}
	}
	
	
	public void dispose(){
		//map.dispose();
		batch.dispose();
	}
	
	public static Pacman getPacman(){return pacman;}

	
}
