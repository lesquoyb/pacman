package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontFactory {

	
private static HashMap<String,BitmapFont> textures = new HashMap<String, BitmapFont>() ;
	
	
	public static void loadFont(String s, FileHandle f){
		textures.put(s,new BitmapFont(f,false));
	}
	
	public static BitmapFont getFont(String s){
		return textures.get(s);
	}
	
	
	public static void dispose(){
		for(BitmapFont t : textures.values()){
			t.dispose();
		}
	}
	
}
