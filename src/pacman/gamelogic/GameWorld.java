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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameWorld {
	
	private SpriteBatch batch;
	public static Map map;
	private OrthographicCamera camera;
	private ArrayList<Character> characters;
	
	
	
	public GameWorld(){

			characters = new ArrayList<Character>();
			camera = new OrthographicCamera();
			camera.setToOrtho(true); 
			camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
			camera.update();		
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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			map.render(batch);
			for(Character c : characters){
				c.render(batch);
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
