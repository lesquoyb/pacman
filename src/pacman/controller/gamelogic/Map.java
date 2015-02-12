package pacman.controller.gamelogic;

import java.util.ArrayList;

import pacman.controller.resources.ResourceManager;
import pacman.model.gameobjects.Floor;
import pacman.model.gameobjects.GameObject;
import pacman.model.gameobjects.StartingPoint;
import pacman.model.gameobjects.Wall;
import pacman.model.gameobjects.Wormhole;
import pacman.model.generators.MapGenerator;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	

	public enum gameElements{floor,wall,pacman,superpacgum,Bghost,Rghost,Yghost,Gghost,wormhole};
	
	public int width;
	public int height;
	public int nbGum = -1;
	private ArrayList<StartingPoint> startingPoints;	
	public ArrayList<ArrayList<GameObject>> grid;
	public static final int tileWidth = 64;
	public static final int tileHeight = 64;
	
	
	public Map(FileHandle file)  throws Exception{
		MapGenerator mg = new MapGenerator(file,this);
		grid = new ArrayList<ArrayList<GameObject>>();
		startingPoints = new ArrayList<StartingPoint>();
		if (mg.generate() == false){
			System.out.println(mg.errorMessage);
			throw new Exception("La map '"+ file.name() +"' ne respecte pas le format demandé.\n" + mg.errorMessage);
		}
	
	}

	public GameObject getElement(int x, int y){
		if(x < grid.size() && y < grid.get(x).size()){
			return grid.get(x).get(y);
		}
		else{
			return null;
		}
	}

	private int tmpX,tmpY;
	public Floor getFloor(float x, float y){
		tmpX = (int)x/tileWidth ;
		tmpY =  (int)y/tileHeight;
		if(tmpX < grid.size() && tmpY < grid.get(tmpX).size()){
			return  ( grid.get(tmpX).get(tmpY) instanceof Floor)? (Floor) grid.get(tmpX).get(tmpY) : null;
		}
		return null;
		
	}
	public GameObject getCellFromFloatPosition(float x, float y){
		tmpX = (int)x/tileWidth ;
		tmpY =  (int)y/tileHeight;
		if(tmpX < grid.size() && tmpY < grid.get(tmpX).size()){
			return  grid.get(tmpX).get(tmpY);
		}
		return null;
	}
	
	
	public GameObject getObstacle(float x, float y){
		tmpX = (int)x/tileWidth ;
		tmpY =  (int)y/tileHeight;
		if(tmpX < grid.size() && tmpY < grid.get(tmpX).size()){
			return  ( grid.get(tmpX).get(tmpY) instanceof Wall || grid.get(tmpX).get(tmpY) instanceof Wormhole)? grid.get(tmpX).get(tmpY) : null;
		}
		return null;
	}
	
	public void addWall(int x, int y){
		Wall wall =  new Wall(x, y, tileWidth, tileHeight );
		addElementToGrid(wall);
	}
	

	public void addFloor(int x, int y,boolean hasGum){
		Floor floor = (new Floor(x, y, tileWidth, tileHeight,hasGum));
		addElementToGrid(floor);
	}
	
	public Wormhole addWormhole(int x, int y, int id){
		Wormhole w = new Wormhole(x, y, tileWidth, tileHeight, id);
		addElementToGrid(w);
		return w;
	}
	
	public ArrayList<ArrayList<GameObject>> getGrid(){
		return grid;
	}
	
	public ArrayList<StartingPoint> getStartingPoints(){
		return startingPoints;
	}
	
	
	public void addStartingPoint(int x, int y, StartingPoint.characters c){
		StartingPoint s = new StartingPoint(x, y, tileWidth, tileHeight, c);
		startingPoints.add(s);
		addElementToGrid(s);
	}
	
	private void addElementToGrid(GameObject o){
		if(o.x == grid.size()){
			grid.add(new ArrayList<GameObject>());
		}
		grid.get(o.x).add(o);
	}
	
	public void update(float delta){
		for(ArrayList<GameObject> list: grid){
			for(GameObject object : list){
				object.update(delta);
			}
		}
	}
	
	public void render(SpriteBatch batch){
		for(ArrayList<GameObject> list: grid){
			for(GameObject object : list){
				batch.draw(ResourceManager.getTexture(object.getAnimation(),object.isAnimated()), object.left, object.top);
			}
		}
	}
	
	
	
}
