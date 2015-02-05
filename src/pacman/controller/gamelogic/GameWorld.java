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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

	
	
	
	public GameWorld(){

			characters = new ArrayList<Character>();
			camera = new OrthographicCamera();
			camera.setToOrtho(true); 
			viewport =  new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			viewport.setCamera(camera);
			viewport.apply();
			camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);


			ResourceManager.setFontColor(ResourceManager.menuFont, Color.BLACK);
			batch = new SpriteBatch();
			batchInv = new SpriteBatch();
			try {
				map = new Map(  Gdx.files.internal("config/map1.map") );
			
			
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la création de la map"+ e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
	
	
	public void render(){
		
		
		camera.update();
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			map.render(batch);
			for(Character c : characters){
				batch.draw(ResourceManager.getTexture(c.getAnimation()),c.left,c.top);
//				c.render(batch);
			}
		batch.end();
		batchInv.begin();
		batchInv.draw(ResourceManager.getTexture(ResourceManager.fondScore),0, Gdx.graphics.getHeight()- ResourceManager.getTexture(ResourceManager.fondScore).getHeight());
		ResourceManager.getFont(ResourceManager.menuFont).draw(batchInv, score + "/" + map.nbGum, 0, Gdx.graphics.getHeight());
		batchInv.end();
	}
	
	public void resize(int width,int height){
		viewport.update(width, height);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight / 2, 0);

	}
	
	public void update(float delta){
		
		map.update(delta);
		for(Character c : characters){
			c.update(delta);
			if(c instanceof Pacman){
				score = ((Pacman) c).eatedGum;
			}
		}
	}
	
	
	public void dispose(){
		map.dispose();
		batch.dispose();
	}
	
	public static Pacman getPacman(){return pacman;}

	
}
