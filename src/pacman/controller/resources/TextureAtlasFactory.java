package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasFactory {

	
	
	private static HashMap<String,TextureAtlas> textures = new HashMap<String, TextureAtlas>() ;
	
	
	public static void loadTextureAtlas(String s, FileHandle f){
		textures.put(s,new TextureAtlas(f));
	}
	
	public static TextureAtlas getTextAtlas(String s){
		return textures.get(s);
	}
	
	
	public static void dispose(){
		for(TextureAtlas t : textures.values()){
			t.dispose();
		}
	}
}
