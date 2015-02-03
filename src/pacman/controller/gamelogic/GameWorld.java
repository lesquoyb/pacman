package pacman.controller.gamelogic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import pacman.controller.resources.ResourceManager;
import pacman.model.gameobjects.BlueGhost;
import pacman.model.gameobjects.Character;
import pacman.model.gameobjects.GreenGhost;
import pacman.model.gameobjects.Pacman;
import pacman.model.gameobjects.RedGhost;
import pacman.model.gameobjects.StartingPoint;
import pacman.model.gameobjects.StartingPoint.characters;
import pacman.model.gameobjects.YellowGhost;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;


public class GameWorld {
	
	private SpriteBatch batch;
	public static Map map;
	private OrthographicCamera camera;
	private ScalingViewport viewport;
	private ArrayList<Character> characters;
	
	
	
	public GameWorld(){

			characters = new ArrayList<Character>();
			camera = new OrthographicCamera();
			camera.setToOrtho(true); 
			camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
			camera.update();
			viewport =  new ScalingViewport(Scaling.fit,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			viewport.setCamera(camera);
			
			batch = new SpriteBatch();
			try {
				map = new Map(  Gdx.files.internal("config/map1.map") );
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				Gdx.app.exit();
			}

			camera.update();
			for(StartingPoint startingPoint : map.getStartingPoints()){
				characters c = startingPoint.getCharacter();
				int x = startingPoint.getX();
				int y = startingPoint.getY();
				Character character = null;
				switch(c){
					case pacman:
						character = new Pacman(x, y, map.tileWidth, map.tileHeight);
						break;
					case Bghost :
						character = new BlueGhost(x,y, map.tileWidth, map.tileHeight);
						break;
					case Rghost :
						character = new RedGhost(x,y, map.tileWidth, map.tileHeight);
						break;
					case  Yghost:
						character = new YellowGhost(x,y, map.tileWidth, map.tileHeight);
						break;
					case Gghost :
						character = new GreenGhost(x,y, map.tileWidth, map.tileHeight);
						break;
				}
					characters.add(character);
			}


	}
	
	
	public void render(){

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			map.render(batch);
			for(Character c : characters){
				batch.draw(ResourceManager.getTexture(c.animation),c.left,c.top);
//				c.render(batch);
			}
		batch.end();
	}
	
	public void resize(int width,int height){
		camera.viewportHeight = height;
		camera.viewportWidth = width;	
	}
	
	public void update(float delta){
		map.update(delta);
		for(Character c : characters){
			c.update(delta);
		}
	}
	
	
	public void dispose(){
		map.dispose();
		batch.dispose();
	}

	
}
