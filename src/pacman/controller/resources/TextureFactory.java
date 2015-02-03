package pacman.controller.resources;


import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public final class TextureFactory {

	private static HashMap<String,Texture> textures = new HashMap<String, Texture>() ;
	private static Texture currentText = null;
	
	
	public static void loadTexture(String s, FileHandle f){
		textures.put(s,new Texture(f));
	}
	
	public static Texture getTexture(String s){
		return textures.get(s);
	}
	
}
