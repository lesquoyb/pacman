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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameWorld {
	
	private SpriteBatch batch;
	public static Map map;
	public OrthographicCamera camera;
	private FitViewport viewport;
	public static ArrayList<Character> characters;
	private static Pacman pacman = null;
	public int score;
	private static final float fpsMin = 1/40f;
	public static int secondsToEnd;
	public static Pathfinder pathfinder;
	public final int TIMER_MAX = 300;
	public final int GUM_VALUE = 100;
	public final int TIME_VALUE = 10;
	public static int remainingLife = 3;
	public static int totalGumEated = 0;
	public static boolean begin ;
	public byte chrono;

	private final TextureRegion circle = ResourceManager.getTexture(ResourceManager.redCircle, false);
	private final int circleHeight = circle.getRegionHeight();

	
	public GameWorld(){
			
		remainingLife = 3;
		totalGumEated = 0;
		
			secondsToEnd = TIMER_MAX;
			characters = new ArrayList<Character>();
		


			batch = new SpriteBatch();
			try {
				map = new Map(  Gdx.files.internal("config/original.map") );
			
			
			} catch (Exception e) {
				//TODO ouvrir une f�netre pour afficher l'erreur
				System.out.println("Une erreur est survenue lors de la cr�ation de la map: "+ e.getMessage());
				Gdx.app.exit();
			}
			
			camera = new OrthographicCamera();
			camera.setToOrtho(true); 

			viewport =  new FitViewport(map.width*Map.tileWidth, map.height*Map.tileHeight);
			viewport.setCamera(camera);
			viewport.apply();
			camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);
			
			pathfinder = new Pathfinder(map.grid);
			camera.update();
			
			newLife();


	}
	
	
	public void newLife(){
		
		
		begin = false;
		

		chrono = 4;
		
		if(ResourceManager.getSound(ResourceManager.mainTheme).isPlaying()){
			ResourceManager.getSound(ResourceManager.mainTheme).stop();
		}
		
		characters.clear();
		
		placeCharacters();
		Timer t = new Timer();
		t.scheduleTask(new Task() {

			
			@Override
			public void run() {
				chrono--;
				if(chrono == 0){
					begin();
				}
			}
		}, 0, 1,3);
		
	}
	
	
	private void begin(){
		begin = true;
		ResourceManager.getSound(ResourceManager.mainTheme).setLooping(true);
		ResourceManager.getSound(ResourceManager.mainTheme).play();		
	}
	
	private void placeCharacters(){
		for(StartingPoint startingPoint : map.getStartingPoints()){
			characters c = startingPoint.getCharacter();
			int x = startingPoint.getX();
			int y = startingPoint.getY();
			Character character = null;
			switch(c){
				case pacman:
					character = new Pacman(x, y, Map.tileWidth, Map.tileHeight);
					pacman = (Pacman) character;
					pacman.update(0);
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
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			map.render(batch);
			for(Character c : characters){
				texture = ResourceManager.getTexture(c.getAnimation(),c.isAnimated());
				batch.draw(circle,pacman.left, pacman.bottom - circleHeight);
				batch.draw( texture, c.left + (Map.tileWidth - texture.getRegionWidth())/2 , c.top + (Map.tileWidth - texture.getRegionHeight()));
			}
		batch.end();
		
	}
	
	
	
	public static Character getCharacterAtPos(int x, int y,Character test){
		for(Character c : characters){
			if(c.getX() == x && c.getY() == y && c != test){
				return c;
			}
		}
		return null;
	}
	

	
	public void resize(int width,int height){
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);
		camera.update();
		
	}
	
	private float dt;
	public void update(float delta){
		if(begin){
			AnimationFactory.update(delta);
			dt = Math.min(delta,fpsMin);
			map.update(dt);
			for(Character c : characters){
				c.update(dt);
				if(c instanceof Pacman){
					score = totalGumEated * GUM_VALUE + ( secondsToEnd - TIMER_MAX)* TIME_VALUE ;
				}
			}
		}
	}
	
	
	public void dispose(){
		if(ResourceManager.getSound(ResourceManager.mainTheme).isPlaying()){
			ResourceManager.getSound(ResourceManager.mainTheme).stop();
		}
		
		batch.dispose();
	}
	
	public static Pacman getPacman(){return pacman;}

	
}
