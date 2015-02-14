package pacman.controller.resources;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasFactory {

	
	
	private static HashMap<String,TextureAtlas> textures = new HashMap<String, TextureAtlas>() ;
	
	
	public static TextureAtlas getTextAtlas(String s){
		TextureAtlas temp = textures.get(s);
		if(temp == null){
			textures.put(s, new TextureAtlas(Gdx.files.internal(ResourceManager.files.get(s))));
			temp = textures.get(s);
		}
		return temp;
	}
	
	
	public static void dispose(){
		for(TextureAtlas t : textures.values()){
			t.dispose();
		}
		textures.clear();
	}
}
