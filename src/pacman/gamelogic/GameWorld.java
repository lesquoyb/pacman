package pacman.gamelogic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import pacman.gameobjects.BlueGhost;
import pacman.gameobjects.Character;
import pacman.gameobjects.GreenGhost;
import pacman.gameobjects.Pacman;
import pacman.gameobjects.RedGhost;
import pacman.gameobjects.StartingPoint;
import pacman.gameobjects.StartingPoint.characters;
import pacman.gameobjects.YellowGhost;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


public class GameWorld {
	
	private SpriteBatch batch;
	public static Map map;
	private OrthographicCamera camera;
	public static World world;
	private Box2DDebugRenderer debugRenderer;
	private ArrayList<Character> characters;
	
	
	
	public GameWorld(){

			world = new World(new Vector2(0,0),true);
			//world.setContactListener(new ContactListener());
			debugRenderer = new Box2DDebugRenderer();
			characters = new ArrayList<Character>();
			camera = new OrthographicCamera();
			camera.setToOrtho(false); 
			camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
			camera.update();		
			batch = new SpriteBatch();
			FileHandle map1 = Gdx.files.internal("config/map1.map");
		try {
			map = new Map( map1 );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			Gdx.app.exit();
		}
			camera.projection.translate(new Vector3( - Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0));
			for(StartingPoint startingPoint : map.getStartingPoints()){
				characters c = startingPoint.getCharacter();
				byte x = startingPoint.getX();
				byte y = startingPoint.getY();
				Character character = null;
				switch(c){
					case pacman:
						character = new Pacman(x, y);
						break;
					case Bghost :
						character = new BlueGhost(x,y);
						break;
					case Rghost :
						character = new RedGhost(x,y);
						break;
					case  Yghost:
						character = new YellowGhost(x,y);
						break;
					case Gghost :
						character = new GreenGhost(x,y);
						break;
				}
					characters.add(character);
			}


	}
	
	
	public void render(){

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
			map.render(batch);
			for(Character c : characters){
				c.render(batch);
			}
			//debugRenderer.render(world, camera.projection);
		batch.end();
	}
	
	public void resize(int width,int height){
		camera.viewportHeight = height;
		camera.viewportWidth = width;	
	}
	
	public void update(){
		map.update();
		world.step(0.12f, 6, 2);
		for(Character c : characters){
			c.update();
		}
	}
	
	
	public void dispose(){
		world.dispose();
		map.dispose();
		batch.dispose();
		debugRenderer.dispose();
	}

	
}
