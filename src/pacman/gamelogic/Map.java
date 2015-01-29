package pacman.gamelogic;

import java.util.ArrayList;

import pacman.gameobjects.GameObject;
import pacman.gameobjects.StartingPoint;
import pacman.gameobjects.Wall;
import pacman.generators.MapGenerator;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {
	
	private ArrayList<GameObject> elements;
	private byte width;
	private byte height;
	private ArrayList<StartingPoint> startingPoints;	
	public static final int tileWidth = 32;
	public static final int tileHeight = 32;
	
	public Map(FileHandle file) throws Exception{
		elements = new ArrayList<GameObject>();
		MapGenerator mg = new MapGenerator(file,this);
		startingPoints = new ArrayList<StartingPoint>();
		if (mg.generate() == false){
			System.out.println(mg.errorMessage);
			throw new Exception("La map '"+ file.name() +"' ne respecte pas le format demandé.\n" + mg.errorMessage);
		}
	}
	
	
	public boolean isWall(Vector2 v){
		for (GameObject g: elements){
			if(g instanceof Wall){
				int x = (int) (v.x / tileWidth);
				int y = (int) (v.y / tileHeight);
				if (g.getX() == x && g.getY() == y){
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<GameObject> getElements(){
		return elements;
	}
	
	public ArrayList<StartingPoint> getStartingPoints(){
		return startingPoints;
	}
	
	public void setWidth(byte width){
		this.width = width;
	}
	
	public void setHeight(byte height){
		this.height = height;
	}
	public byte getWidth(){
		return width;
	}
	
	public byte getHeight(){
		return height;
	}
	public void addElement(GameObject o ){
		elements.add(o);
	}
	
	public void addStartingPoint(StartingPoint s){
		addElement(s);
		startingPoints.add(s);
	}
	
	public void update(){
		for(GameObject object : elements){
			object.update();
		}
	}
	
	public void render(SpriteBatch batch){
		for(GameObject object : elements){
			object.render(batch);
		}
	}
	
	
	public void dispose(){
		for(GameObject object: elements){
			object.dispose();
		}
	}
}
