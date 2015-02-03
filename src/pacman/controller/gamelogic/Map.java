package pacman.controller.gamelogic;

import java.util.ArrayList;

import pacman.controller.resources.ResourceManager;
import pacman.model.gameobjects.BlueGhost;
import pacman.model.gameobjects.Floor;
import pacman.model.gameobjects.GameObject;
import pacman.model.gameobjects.GreenGhost;
import pacman.model.gameobjects.Pacman;
import pacman.model.gameobjects.RedGhost;
import pacman.model.gameobjects.StartingPoint;
import pacman.model.gameobjects.Wall;
import pacman.model.gameobjects.Wormhole;
import pacman.model.gameobjects.YellowGhost;
import pacman.model.generators.MapGenerator;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	

	enum gameElements{floor,wall,pacman,superpacgum,Bghost,Rghost,Yghost,Gghost,wormhole};
	
	private ArrayList<GameObject> elements;
	public int width;
	public int height;
	private ArrayList<StartingPoint> startingPoints;	
	public static final int tileWidth = 32;
	public static final int tileHeight = 32;
	public gameElements[][] matrix;
	public Map(FileHandle file){// throws Exception{
		elements = new ArrayList<GameObject>();
		MapGenerator mg = new MapGenerator(file,this);
		startingPoints = new ArrayList<StartingPoint>();
		if (mg.generate() == false){
			System.out.println(mg.errorMessage);
//			throw new Exception("La map '"+ file.name() +"' ne respecte pas le format demandé.\n" + mg.errorMessage);
		}
		createMatrix();
	}
	
	private void createMatrix(){
		matrix = new gameElements[width][height];

		int i = 0,j = 0;
		for(GameObject object : elements){
			if(object instanceof Wall){
				matrix[i][j] = gameElements.wall;
			}
			else if(object instanceof Floor){
				matrix[i][j] = gameElements.floor;
			}
			else if(object instanceof StartingPoint){
				
				switch(((StartingPoint) object).getCharacter()){
					case pacman:
						matrix[i][j] = gameElements.pacman;
						break;
					case Bghost :
						matrix[i][j] = gameElements.Bghost;
						break;
					case Rghost :
						matrix[i][j] = gameElements.Rghost;
						break;
					case  Yghost:
						matrix[i][j] = gameElements.Yghost;
						break;
					case Gghost :
						matrix[i][j] = gameElements.Gghost;
						break;
					default:
						assert false;
				}
			}
			/*
			else if(object instanceof SuperPacGum){
				matrix[i][j] = gameElements.superpacgum;					
			}
			*/
			else if(object instanceof Wormhole){
				matrix[i][j] = gameElements.wormhole;
				
			}
			else{
				assert false;
			}
			
			i++;
			if(i == width){
				j++;
				i =0;
			}
			if(j == height){
				j = 0;
				assert elements.iterator().hasNext() == false;
			}
		}
		assert j == i && i == 0;
			System.out.println(matrix);
		
	}
	public GameObject getObstacle(float x, float y){
		
		for (GameObject g: elements){
			if(g instanceof Wall || g instanceof Wormhole){
				if (g.getX() == (int)x/tileWidth  && g.getY() == (int)y/tileHeight){
					return g;
				}
			}
		}
		return null;
	}
	
	public void addWall(int x, int y){
		elements.add( new Wall(x, y, tileWidth, tileHeight ) );
	}
	

	public void addFloor(int x, int y){
		elements.add(new Floor(x, y, tileWidth, tileHeight));
	}
	
	public Wormhole addWormhole(int x, int y, int id){
		Wormhole w = new Wormhole(x, y, tileWidth, tileHeight, id);
		elements.add(w);
		return w;
	}
	
	public ArrayList<GameObject> getElements(){
		return elements;
	}
	
	public ArrayList<StartingPoint> getStartingPoints(){
		return startingPoints;
	}
	
	
	public void addStartingPoint(int x, int y, StartingPoint.characters c){
		StartingPoint s = new StartingPoint(x, y, tileWidth, tileHeight, c);
		elements.add(s);
		startingPoints.add(s);
	}
	
	public void update(float delta){
		for(GameObject object : elements){
			object.update(delta);
		}
	}
	
	public void render(SpriteBatch batch){
		for(GameObject object : elements){
			batch.draw(ResourceManager.getTexture(object.animation), object.left, object.top);
//			
//			object.render(batch);
		}
	}
	
	
	public void dispose(){
		for(GameObject object: elements){
//			object.dispose();
		}
	}
}
