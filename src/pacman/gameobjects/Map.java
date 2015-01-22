package pacman.gameobjects;

import java.util.ArrayList;

import pacman.generators.MapGenerator;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	
	public ArrayList<GameObject> elements;
	public byte width;
	public byte height;
	
	public Map(FileHandle file) throws Exception{
		elements = new ArrayList<GameObject>();
		MapGenerator mg = new MapGenerator(file,this);
		if (mg.generate() == false){
			System.out.println(mg.errorMessage);
			throw new Exception("La map '"+ file.name() +"' ne respecte pas le format demandé.\n" + mg.errorMessage);
		}
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
	
}
